package com.rentaplace.reviews.service;

import com.rentaplace.reviews.dao.ReviewsDao;
import com.rentaplace.reviews.exception.ReviewsException;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.units.exception.UnitsException;
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
