package com.rentaplace.reviews.exception;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.reviews.exception.error.ReviewsErrorCodes;
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
