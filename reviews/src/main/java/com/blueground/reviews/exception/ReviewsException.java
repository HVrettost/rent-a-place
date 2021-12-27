package com.blueground.reviews.exception;

import com.blueground.common.exception.MarsRentalCoreException;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import lombok.Getter;

@Getter
public class ReviewsException extends MarsRentalCoreException {

    private final ReviewsErrorCodes reviewsErrorCodes;

    public ReviewsException(ReviewsErrorCodes reviewsErrorCodes, Throwable throwable) {
        super(throwable);
        this.reviewsErrorCodes = reviewsErrorCodes;
    }

    public ReviewsException(ReviewsErrorCodes reviewsErrorCodes) {
        this.reviewsErrorCodes = reviewsErrorCodes;
    }

}
