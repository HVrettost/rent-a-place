package com.blueground.reviews.validator

import com.blueground.reviews.exception.ReviewsException
import com.blueground.reviews.exception.error.ReviewsErrorCodes
import com.blueground.reviews.model.dto.ReviewDto
import spock.lang.Specification
import spock.lang.Unroll

class ReviewsDtoValidatorTest extends Specification {

    private ReviewDtoValidator validator

    def setup() {
        validator = new ReviewDtoValidator()
    }

    @Unroll
    def "should throw exception if score is not between minimum and maximum value"() {
        given:
            def review = new ReviewDto(score: score, unitId: UUID.randomUUID(), userId: UUID.randomUUID())

        when:
            validator.validate(review)

        then:
            def exceptionThrown = thrown(ReviewsException)
            exceptionThrown.reviewsErrorCodes == ReviewsErrorCodes.INVALID_SCORE

        where:
            score << [-1, 0, 6, -100, 7, 900]
    }

    def "should throw exception if unit id is not existent in dto"() {
        given:
            def review = new ReviewDto(score: 3, userId: UUID.randomUUID())

        when:
            validator.validate(review)

        then:
            def exceptionThrown = thrown(ReviewsException)
            exceptionThrown.reviewsErrorCodes == ReviewsErrorCodes.UNIT_ID_NOT_FOUND
    }

    def "should throw exception if user id is not existent in dto"() {
        given:
            def review = new ReviewDto(score: 3, unitId: UUID.randomUUID())

        when:
            validator.validate(review)

        then:
            def exceptionThrown = thrown(ReviewsException)
            exceptionThrown.reviewsErrorCodes == ReviewsErrorCodes.USER_ID_NOT_FOUND
    }
}
