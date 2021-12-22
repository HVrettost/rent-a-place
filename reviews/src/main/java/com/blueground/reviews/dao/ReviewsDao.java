package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.units.exception.UnitsException;

import java.util.UUID;

public interface ReviewsDao {

    void insertReviewInDatabase(ReviewDto review) throws ReviewsException;

    Integer calculateReviewsAverageScoreForUnit(UUID unitId) throws ReviewsException;

    void updateAverageScoreForUnit(Integer averageScore, UUID unitId) throws UnitsException;

}
