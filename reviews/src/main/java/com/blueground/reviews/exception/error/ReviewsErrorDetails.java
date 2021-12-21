package com.blueground.reviews.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class ReviewsErrorDetails {

    private final int errorCode;
    private final String description;
    @JsonIgnore
    private final HttpStatus httpStatus;
}
