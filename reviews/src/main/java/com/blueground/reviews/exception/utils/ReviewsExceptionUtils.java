package com.blueground.reviews.exception.utils;

import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.reviews.exception.error.ReviewsErrorDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewsExceptionUtils {

    public ReviewsErrorDetails createErrorDetails(ReviewsErrorCodes error) {
        return new ReviewsErrorDetails(error.getApplicationErrorCode(), error.getDescription(), error.getHttpStatus());
    }
}
