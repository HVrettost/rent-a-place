package com.blueground.functionaltests.exception

import org.springframework.http.HttpStatus

enum UnitsErrorCodes {

    GENERIC_UNITS_ERROR(0, "Something went really bad", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPTY_SEARCH_VALUE(100, "The value you passed is empty", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_REQUEST(200, "Invalid page request", HttpStatus.BAD_REQUEST),
    COULD_NOT_UPDATE_SCORE(300, "Could not update score for unit", HttpStatus.CONFLICT);

    private final int applicationErrorCode
    private final String description
    private final HttpStatus httpStatus

    UnitsErrorCodes(int applicationErrorCode, String description, HttpStatus httpStatus) {
        this.applicationErrorCode = applicationErrorCode
        this.description = description
        this.httpStatus = httpStatus
    }

    int getApplicationErrorCode() {
        this.applicationErrorCode
    }

    String getDescription() {
        this.description
    }

    HttpStatus getHttpStatus() {
        this.httpStatus
    }
}
