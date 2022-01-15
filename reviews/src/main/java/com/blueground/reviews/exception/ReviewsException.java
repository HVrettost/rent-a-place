package com.blueground.reviews.exception;

import com.blueground.common.exception.RentAPlaceCoreException;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import lombok.Getter;

@Getter
public class ReviewsException extends RentAPlaceCoreException {

    private final ReviewsErrorCodes reviewsErrorCodes;

    public ReviewsException(ReviewsErrorCodes reviewsErrorCodes, Throwable throwable) {
        super(throwable);
        this.reviewsErrorCodes = reviewsErrorCodes;
    }

    public ReviewsException(ReviewsErrorCodes reviewsErrorCodes) {
        this.reviewsErrorCodes = reviewsErrorCodes;
    }

}
