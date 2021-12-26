package com.blueground.auth.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthenticationErrorCodes {

    GENERIC_AUTHENTICATION_ERROR(0, "Auth Generic Error", HttpStatus.BAD_REQUEST),
    BAD_CREDENTIALS(100, "Username and password could not be verified", HttpStatus.UNAUTHORIZED);

    private final int applicationErrorCode;
    private final String description;
    private final HttpStatus httpStatus;
}
