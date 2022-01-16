package com.rentaplace.reviews.service;

import com.rentaplace.reviews.exception.ReviewsException;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.units.exception.UnitsException;

public interface ReviewsService {

    void saveReviewAndUpdateAverageScoreForUnit(ReviewDto reviewDto) throws ReviewsException, UnitsException;
}
