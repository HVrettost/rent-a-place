package com.blueground.functionaltests.core.reviews

import com.blueground.functionaltests.config.MarsRentalFTSetup
import com.blueground.functionaltests.domain.Review
import com.blueground.functionaltests.domain.Unit
import com.blueground.functionaltests.domain.User
import com.blueground.functionaltests.dto.UnitCreationRequestDto
import com.blueground.functionaltests.dto.UserCreationRequestDto
import com.blueground.functionaltests.exception.ErrorDetails
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.exception.ReviewsErrorCodes
import com.blueground.functionaltests.utils.UserUtils
import spock.lang.Unroll

class ReviewsSpec extends MarsRentalFTSetup implements UserUtils {

    def cleanupSpec() {
        systemActor.deleteAllUnitsFromDatabase(restTemplate)
        systemActor.deleteAllUsersFromDatabase(restTemplate)
    }

    def "should return exception if passed review object does not contain user id"() {
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and: 'a review'
            Review reviewDto = new Review(score: 3, comment: 'a comment', unitId: UUID.randomUUID())

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto, cookieWithAccessToken, userAgent)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.USER_ID_NOT_FOUND.applicationErrorCode
                description == ReviewsErrorCodes.USER_ID_NOT_FOUND.description
            }
    }

    def "should return exception if passed review object does not contain unit id"() {
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and:
            Review reviewDto = new Review(score: 3, comment: 'a comment', userId: user.userId)

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto, cookieWithAccessToken, userAgent)
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
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and:
            Review reviewDto = new Review(score: score, comment: 'a comment', userId: user.userId)

        when:
            HttpErrorResponse response = userActor.saveReview(restTemplate, reviewDto, cookieWithAccessToken, userAgent)
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
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and: 'a unit'
            Unit unit = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region', 'title', new BigDecimal("500")))

        and: 'a review for certain user for the same unit'
            Review firstReview = new Review(score: 3, comment: 'a comment', userId: user.userId, unitId: unit.unitId)
            Review secondReview = new Review(score: 4, comment: 'another comment', userId: user.userId, unitId: unit.unitId)

        when: 'user tries to save the first review'
            def response = userActor.saveReview(restTemplate, firstReview, cookieWithAccessToken, userAgent)

        then: 'the review is successfully saved'
            response.status == 204

        and: 'the average score of the unit has changed'
            systemActor.getAverageScore(restTemplate, unit.unitId) == firstReview.score

        when: 'user tries to save the second review'
            HttpErrorResponse errorResponse = userActor.saveReview(restTemplate, secondReview, cookieWithAccessToken, userAgent)
            def deserializedResponse = objectMapper.readValue(errorResponse.errorResponseAsString, ErrorDetails)

        then: 'an exception is thrown stating that the review for the given unit already exists'
            errorResponse.rawHttpStatus == 409
            with(deserializedResponse) {
                applicationErrorCode == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.applicationErrorCode
                description == ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.description
            }
    }

    def "should calculate average score correctly after more than one users have reviewed the same unit"() {
        given: 'three users with access token'
            def username1 = UUID.randomUUID().toString()
            def username2 = UUID.randomUUID().toString()
            def username3 = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username1, password)
            UserCreationRequestDto request2 = createUserRequest(username2, password)
            UserCreationRequestDto request3 = createUserRequest(username3, password)
            User user1 = systemActor.createNewUser(restTemplate, request)
            User user2 = systemActor.createNewUser(restTemplate, request2)
            User user3 = systemActor.createNewUser(restTemplate, request3)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken1 = createAccessTokenForUser(userAgent, username1, password)
            def cookieWithAccessToken2 = createAccessTokenForUser(userAgent, username2, password)
            def cookieWithAccessToken3 = createAccessTokenForUser(userAgent, username3, password)

        and: 'a unit'
            Unit unit = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region', 'title', new BigDecimal("500")))

        and: 'three reviews for the same unit by three different users'
            Review reviewByUser1 = new Review(score: 5, comment: 'a comment', userId: user1.userId, unitId: unit.unitId)
            Review reviewByUser2 = new Review(score: 3, comment: 'a comment', userId: user2.userId, unitId: unit.unitId)
            Review reviewByUser3 = new Review(score: 3, comment: 'a comment', userId: user3.userId, unitId: unit.unitId)

        when: 'all users send their reviews'
            userActor.saveReview(restTemplate, reviewByUser1, cookieWithAccessToken1, userAgent)
            userActor.saveReview(restTemplate, reviewByUser2, cookieWithAccessToken2, userAgent)
            userActor.saveReview(restTemplate, reviewByUser3, cookieWithAccessToken3, userAgent)

        then: 'the average score of the unit has changed'
            systemActor.getAverageScore(restTemplate, unit.unitId) == Math.round((reviewByUser1.score + reviewByUser2.score + reviewByUser3.score) / 3)

        and: 'total reviews number in the database for the unit is 3'
            systemActor.getReviewsByUnitId(restTemplate, unit.unitId).length == 3
    }

}
