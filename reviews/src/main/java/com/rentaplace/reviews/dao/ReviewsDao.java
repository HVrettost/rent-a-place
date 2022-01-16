package com.rentaplace.reviews.dao;

import com.rentaplace.reviews.exception.ReviewsException;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.units.exception.UnitsException;
import lombok.NonNull;

import java.util.UUID;

public interface ReviewsDao {

    void insertReviewInDatabase(@NonNull ReviewDto review);

    Integer calculateReviewsAverageScoreForUnit(UUID unitId) throws ReviewsException;

    void updateAverageScoreForUnit(Integer averageScore, UUID unitId) throws UnitsException;

}
