package com.blueground.reviews.service;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.units.exception.UnitsException;

public interface ReviewsService {

    void saveReviewAndUpdateAverageScoreForUnit(ReviewDto reviewDto) throws ReviewsException, UnitsException;
}
