package com.blueground.units.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UnitsErrorCodes {

    GENERIC_UNITS_ERROR(0, "Something went really bad", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPTY_SEARCH_VALUE(100, "The value you passed is empty", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_REQUEST(200, "Invalid page request", HttpStatus.BAD_REQUEST),
    COULD_NOT_UPDATE_SCORE(300, "Could not update score for unit", HttpStatus.CONFLICT),
    INVALID_STORE_UNIT_REQUEST(400, "Could not store one or more units", HttpStatus.BAD_REQUEST);

    private final int applicationErrorCode;
    private final String description;
    private final HttpStatus httpStatus;
}
