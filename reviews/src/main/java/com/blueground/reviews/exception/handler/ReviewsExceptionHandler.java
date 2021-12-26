package com.blueground.reviews.exception.handler;

import com.blueground.common.exception.error.ErrorDetails;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.blueground.reviews.controller")
public class ReviewsExceptionHandler {

    @ExceptionHandler(value = ReviewsException.class)
    public ResponseEntity<ErrorDetails> handleReviewsException(ReviewsException rex) {
        return new ResponseEntity<>(createErrorDetails(rex.getReviewsErrorCodes()), rex.getReviewsErrorCodes().getHttpStatus());
    }

    @ExceptionHandler(value = UnitsException.class)
    public ResponseEntity<ErrorDetails> handleUnitsException(UnitsException uex) {
        return new ResponseEntity<>(createErrorDetails(uex.getUnitsErrorCodes()), uex.getUnitsErrorCodes().getHttpStatus());
    }

    public ErrorDetails createErrorDetails(ReviewsErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }

    public ErrorDetails createErrorDetails(UnitsErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }

}
