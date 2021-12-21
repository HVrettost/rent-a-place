package com.blueground.reviews.service;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.domain.ReviewDto;

public interface ReviewsService {

    void saveReview(ReviewDto reviewDto) throws ReviewsException;
}
