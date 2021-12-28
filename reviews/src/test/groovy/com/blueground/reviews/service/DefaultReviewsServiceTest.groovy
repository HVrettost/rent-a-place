package com.blueground.reviews.service

import com.blueground.reviews.dao.ReviewsDao
import com.blueground.reviews.model.dto.ReviewDto
import spock.lang.Specification

class DefaultReviewsServiceTest extends Specification {

    private ReviewsDao reviewsDao
    private ReviewsService reviewsService

    def setup() {
        reviewsDao = Mock()
        reviewsService = new DefaultReviewsService(reviewsDao)
    }

    def "should insert review and update average score by propagating call to dao layer"() {
        given:
            ReviewDto reviewDto = new ReviewDto(score: 2, comment: 'a comment', userId: UUID.randomUUID(), unitId: UUID.randomUUID())
            Integer calculatedAverageScoreForUnit = 2

        when:
            reviewsService.saveReviewAndUpdateAverageScoreForUnit(reviewDto)

        then:
            1 * reviewsDao.insertReviewInDatabase(reviewDto)
            1 * reviewsDao.calculateReviewsAverageScoreForUnit(reviewDto.unitId) >> calculatedAverageScoreForUnit
            1 * reviewsDao.updateAverageScoreForUnit(calculatedAverageScoreForUnit, reviewDto.unitId)
            0 * _
    }

}
