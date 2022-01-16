package com.rentaplace.reviews.dao

import com.rentaplace.reviews.exception.ReviewsException
import com.rentaplace.reviews.exception.error.ReviewsErrorCodes
import com.rentaplace.reviews.model.dto.ReviewDto
import com.rentaplace.reviews.model.entity.Review
import com.rentaplace.reviews.repository.ReviewsRepository
import com.rentaplace.units.repository.RetrieveUnitsRepository
import org.springframework.core.convert.converter.Converter
import spock.lang.Specification
import spock.lang.Unroll

class DefaultReviewsDaoTest extends Specification {

    private ReviewsRepository reviewsRepository
    private RetrieveUnitsRepository unitsRepository
    private Converter<ReviewDto, Review> converter
    private ReviewsDao reviewsDao

    def setup() {
        reviewsRepository = Mock()
        unitsRepository = Mock()
        converter = Mock()
        reviewsDao = new DefaultReviewsDao(reviewsRepository, unitsRepository, converter)
    }

    def "should call repository and save review successfully"() {
        given:
            ReviewDto reviewDto = Mock()
            Review convertedDtoToEntity = Mock()

        when:
            reviewsDao.insertReviewInDatabase(reviewDto)

        then:
            1 * converter.convert(reviewDto) >> convertedDtoToEntity
            1 * reviewsRepository.save(convertedDtoToEntity)
            0 * _
    }

    def "should throw exception if returned avg score is null for certain unit"() {
        given:
            def unitId = UUID.randomUUID()

        when:
            reviewsDao.calculateReviewsAverageScoreForUnit(unitId)

        then:
            1 * reviewsRepository.calculateUnitAverageScore(unitId) >> Optional.empty()
            0 * _

        and:
            def exceptionThrown = thrown(ReviewsException)
            exceptionThrown.reviewsErrorCodes == ReviewsErrorCodes.AVERAGE_SCORE_CALCULATION_INABILITY
    }

    @Unroll
    def "should return rounded value of the average score of the unit"() {
        given:
            def unitId = UUID.randomUUID()

        when:
            def response = reviewsDao.calculateReviewsAverageScoreForUnit(unitId)

        then:
            1 * reviewsRepository.calculateUnitAverageScore(unitId) >> Optional.of(averageScore)
            0 * _

        and:
            response == expectedResponse

        where:
            averageScore  | expectedResponse
            3.4f          | 3
            4.8f          | 5
    }

    def "should call repository layer and update the unit's average score"() {
        given:
            def averageScore = 2
            def unitId = UUID.randomUUID()

        when:
            reviewsDao.updateAverageScoreForUnit(averageScore, unitId)

        then:
            1 * unitsRepository.updateUnitAverageScore(averageScore, unitId)
            0 * _
    }
}
