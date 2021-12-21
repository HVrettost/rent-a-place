package com.blueground.reviews.exception;

import com.blueground.reviews.exception.error.ReviewsErrorDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ReviewsException extends Exception {

    private final ReviewsErrorDetails errorDetails;
}
