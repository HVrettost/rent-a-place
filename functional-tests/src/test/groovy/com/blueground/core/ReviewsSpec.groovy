package com.blueground.core

import com.blueground.config.MarsRentalFTSetup
import com.blueground.domain.ReviewDto
import com.blueground.exception.ErrorDetails
import com.blueground.exception.HttpErrorResponse
import com.blueground.exception.ReviewsErrorCodes
import spock.lang.Unroll

class ReviewsSpec extends MarsRentalFTSetup {

    def "should return exception if passed review object does not contain user id"() {
        given:
            ReviewDto reviewDto = new ReviewDto(score: 3, comment: 'a comment', unitId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                errorCode == ReviewsErrorCodes.USER_ID_NOT_FOUND.errorCode
                description == ReviewsErrorCodes.USER_ID_NOT_FOUND.description
            }
    }

    def "should return exception if passed review object does not contain unit id"() {
        given:
            ReviewDto reviewDto = new ReviewDto(score: 3, comment: 'a comment', userId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                errorCode == ReviewsErrorCodes.UNIT_ID_NOT_FOUND.errorCode
                description == ReviewsErrorCodes.UNIT_ID_NOT_FOUND.description
            }
    }

    @Unroll
    def "should return exception if passed review object contains invalid score"() {
        given:
            ReviewDto reviewDto = new ReviewDto(score: score, comment: 'a comment', userId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                errorCode == ReviewsErrorCodes.INVALID_SCORE.errorCode
                description == ReviewsErrorCodes.INVALID_SCORE.description
            }

        where:
            score << [-1, 6, 7]
    }

    def "should return exception if user tries to add review for the second time for the same unit"() {
        given: 'a review for certain user for the same unit'
            def unitId = UUID.randomUUID()
            def userId = UUID.randomUUID()
            ReviewDto firstReview = new ReviewDto(score: 3, comment: 'a comment', userId: userId, unitId: unitId)
            ReviewDto secondReview = new ReviewDto(score: 4, comment: 'another comment', userId: userId, unitId: unitId)

        when: 'user tries to save the first review'
            def response = userActor.saveReview(restTemplate, firstReview)

        then: 'the review is successfully saved'
            response.status == 204

        when: 'user tries to save the second review'
            HttpErrorResponse errorResponse = userActor.saveReview(restTemplate, secondReview)
            def deserializedResponse = objectMapper.readValue(errorResponse.errorResponseAsString, ErrorDetails)

        then: 'an exception is thrown stating that the review for the given unit already exists'
            errorResponse.rawHttpStatus == 409
            with(deserializedResponse) {
                errorCode == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.errorCode
                description == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.description
            }
    }
}
