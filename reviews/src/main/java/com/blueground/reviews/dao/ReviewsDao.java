package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;

public interface ReviewsDao {

    void insertReviewInDatabase(ReviewDto review) throws ReviewsException;

}
