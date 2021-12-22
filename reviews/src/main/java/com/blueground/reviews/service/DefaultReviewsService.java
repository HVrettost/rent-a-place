package com.blueground.reviews.service;

import com.blueground.reviews.dao.ReviewsDao;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.units.exception.UnitsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultReviewsService implements ReviewsService {

    private final ReviewsDao reviewsDao;

    @Override
    @Transactional
    public void saveReviewAndUpdateAverageScoreForUnit(ReviewDto reviewDto) throws ReviewsException, UnitsException {
        reviewsDao.insertReviewInDatabase(reviewDto);
        Integer averageScore = reviewsDao.calculateReviewsAverageScoreForUnit(reviewDto.getUnitId());
        reviewsDao.updateAverageScoreForUnit(averageScore, reviewDto.getUnitId());
    }
}
