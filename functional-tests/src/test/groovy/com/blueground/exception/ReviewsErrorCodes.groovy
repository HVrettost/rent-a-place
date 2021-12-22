package com.blueground.exception

import org.springframework.http.HttpStatus

enum ReviewsErrorCodes {

    GENERIC_REVIEWS_ERROR(0, "Something went really bad", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_REVIEW_SAVE(100, "Could not save review", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_SCORE(200, "Score should be between 0 and 5", HttpStatus.BAD_REQUEST),
    USER_ID_NOT_FOUND(300, "User id could not be found in request", HttpStatus.BAD_REQUEST),
    UNIT_ID_NOT_FOUND(400, "Unit id could not be found", HttpStatus.BAD_REQUEST),
    REVIEW_ALREADY_EXISTS(500, "Review with given user id and unit id already exists", HttpStatus.CONFLICT)

    private final int applicationErrorCode
    private final String description
    private final HttpStatus httpStatus

    ReviewsErrorCodes(int applicationErrorCode, String description, HttpStatus httpStatus) {
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
