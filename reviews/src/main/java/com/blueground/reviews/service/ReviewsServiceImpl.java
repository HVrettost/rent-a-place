package com.blueground.reviews.service;

import com.blueground.reviews.dao.ReviewsDao;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.domain.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsDao reviewsDao;

    @Override
    public void saveReview(ReviewDto reviewDto) throws ReviewsException {
        reviewsDao.insertReviewInDatabase(reviewDto);
    }
}
