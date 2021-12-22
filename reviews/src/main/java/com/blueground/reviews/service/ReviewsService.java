package com.blueground.reviews.service;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;

public interface ReviewsService {

    void saveReview(ReviewDto reviewDto) throws ReviewsException;
}
