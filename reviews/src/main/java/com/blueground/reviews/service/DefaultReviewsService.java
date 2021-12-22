package com.blueground.reviews.service;

import com.blueground.reviews.dao.ReviewsDao;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewsService implements ReviewsService {

    private final ReviewsDao reviewsDao;

    @Override
    public void saveReview(ReviewDto reviewDto) throws ReviewsException {
        reviewsDao.insertReviewInDatabase(reviewDto);
    }
}
