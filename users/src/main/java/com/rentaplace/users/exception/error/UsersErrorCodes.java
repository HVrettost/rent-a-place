package com.rentaplace.users.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UsersErrorCodes {

    GENERIC_USERS_ERROR(0, "Something went really bad", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_DOES_NOT_EXIST(100, "User could not be find with given username", HttpStatus.BAD_REQUEST);

    private final int applicationErrorCode;
    private final String description;
    private final HttpStatus httpStatus;
}
