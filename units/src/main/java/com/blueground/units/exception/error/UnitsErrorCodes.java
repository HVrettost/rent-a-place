package com.blueground.units.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UnitsErrorCodes {

    GENERIC_UNITS_ERROR(0, "Something went really bad", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPTY_SEARCH_VALUE(100, "The value you passed is empty", HttpStatus.BAD_REQUEST);

    private final int applicationErrorCode;
    private final String description;
    private final HttpStatus httpStatus;
}
