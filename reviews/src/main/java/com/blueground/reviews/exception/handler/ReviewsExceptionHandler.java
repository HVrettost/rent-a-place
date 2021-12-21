package com.blueground.reviews.exception.handler;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.exception.error.ReviewsErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.blueground.reviews.controller")
public class ReviewsExceptionHandler {

    @ExceptionHandler(value = ReviewsException.class)
    public ResponseEntity<ReviewsErrorDetails> handleReviewsException(ReviewsException rex) {
        return new ResponseEntity<>(rex.getErrorDetails(), rex.getErrorDetails().getHttpStatus());
    }
}
