package com.blueground.reviews.exception.handler;

import com.blueground.common.exception.error.ErrorDetails;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.blueground.reviews.controller")
public class ReviewsExceptionHandler {

    @ExceptionHandler(value = ReviewsException.class)
    public ResponseEntity<ErrorDetails> handleReviewsException(ReviewsException rex) {
        log.error("An exception occurred with message: {}", rex.getReviewsErrorCodes().getDescription(), rex);
        return new ResponseEntity<>(createErrorDetails(rex.getReviewsErrorCodes()), rex.getReviewsErrorCodes().getHttpStatus());
    }

    @ExceptionHandler(value = UnitsException.class)
    public ResponseEntity<ErrorDetails> handleUnitsException(UnitsException uex) {
        log.error("An exception occurred with message: {}", uex.getUnitsErrorCodes().getDescription(), uex);
        return new ResponseEntity<>(createErrorDetails(uex.getUnitsErrorCodes()), uex.getUnitsErrorCodes().getHttpStatus());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationExceptionException(DataIntegrityViolationException ex) {
        log.error("An exception occurred with message: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorDetails(ReviewsErrorCodes.REVIEW_ALREADY_EXISTS), ReviewsErrorCodes.REVIEW_ALREADY_EXISTS.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        log.error("An exception occurred with message: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorDetails(ReviewsErrorCodes.GENERIC_REVIEWS_ERROR), ReviewsErrorCodes.GENERIC_REVIEWS_ERROR.getHttpStatus());
    }

    public ErrorDetails createErrorDetails(ReviewsErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }

    public ErrorDetails createErrorDetails(UnitsErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }

}
