package com.blueground.core.reviews

import com.blueground.config.MarsRentalFTSetup
import com.blueground.domain.Review
import com.blueground.domain.Unit
import com.blueground.domain.User
import com.blueground.dto.UnitCreationRequestDto
import com.blueground.exception.ErrorDetails
import com.blueground.exception.HttpErrorResponse
import com.blueground.exception.ReviewsErrorCodes
import spock.lang.Unroll

class ReviewsSpec extends MarsRentalFTSetup {

    def cleanupSpec() {
        systemActor.deleteAllUnitsFromDatabase(restTemplate)
    }

    def "should return exception if passed review object does not contain user id"() {
        given:
            Review reviewDto = new Review(score: 3, comment: 'a comment', unitId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.USER_ID_NOT_FOUND.applicationErrorCode
                description == ReviewsErrorCodes.USER_ID_NOT_FOUND.description
            }
    }

    def "should return exception if passed review object does not contain unit id"() {
        given:
            Review reviewDto = new Review(score: 3, comment: 'a comment', userId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.UNIT_ID_NOT_FOUND.applicationErrorCode
                description == ReviewsErrorCodes.UNIT_ID_NOT_FOUND.description
            }
    }

    @Unroll
    def "should return exception if passed review object contains invalid score"() {
        given:
            Review reviewDto = new Review(score: score, comment: 'a comment', userId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.INVALID_SCORE.applicationErrorCode
                description == ReviewsErrorCodes.INVALID_SCORE.description
            }

        where:
            score << [-1, 6, 7]
    }

    def "should return exception if user tries to add review for the second time for the same unit"() {
        given: 'a user'
            User user = systemActor.createNewUser(restTemplate)

        and: 'a unit'
            Unit unit = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region', 'title', new BigDecimal("500")))

        and: 'a review for certain user for the same unit'
            Review firstReview = new Review(score: 3, comment: 'a comment', userId: user.userId, unitId: unit.unitId)
            Review secondReview = new Review(score: 4, comment: 'another comment', userId: user.userId, unitId: unit.unitId)

        when: 'user tries to save the first review'
            def response = userActor.saveReview(restTemplate, firstReview)

        then: 'the review is successfully saved'
            response.status == 204

        and: 'the average score of the unit has changed'
            systemActor.getAverageScore(restTemplate, unit.unitId) == firstReview.score

        when: 'user tries to save the second review'
            HttpErrorResponse errorResponse = userActor.saveReview(restTemplate, secondReview)
            def deserializedResponse = objectMapper.readValue(errorResponse.errorResponseAsString, ErrorDetails)

        then: 'an exception is thrown stating that the review for the given unit already exists'
            errorResponse.rawHttpStatus == 409
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.applicationErrorCode
                description == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.description
            }
    }

    def "should calculate average score correctly after more than one users have reviewed the same unit"() {
        given: 'three users'
            User user1 = systemActor.createNewUser(restTemplate)
            User user2 = systemActor.createNewUser(restTemplate)
            User user3 = systemActor.createNewUser(restTemplate)

        and: 'a unit'
            Unit unit = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region', 'title', new BigDecimal("500")))

        and: 'three reviews for the same unit by three different users'
            Review reviewByUser1 = new Review(score: 5, comment: 'a comment', userId: user1.userId, unitId: unit.unitId)
            Review reviewByUser2 = new Review(score: 3, comment: 'a comment', userId: user2.userId, unitId: unit.unitId)
            Review reviewByUser3 = new Review(score: 3, comment: 'a comment', userId: user3.userId, unitId: unit.unitId)

        when: 'all users send their reviews'
            userActor.saveReview(restTemplate, reviewByUser1)
            userActor.saveReview(restTemplate, reviewByUser2)
            userActor.saveReview(restTemplate, reviewByUser3)

        then: 'the average score of the unit has changed'
            systemActor.getAverageScore(restTemplate, unit.unitId) == Math.round((reviewByUser1.score + reviewByUser2.score + reviewByUser3.score) / 3)

        and: 'total reviews number in the database for the unit is 3'
            systemActor.getReviewsByUnitId(restTemplate, unit.unitId).length == 3
    }
}
