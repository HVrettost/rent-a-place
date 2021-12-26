package com.blueground.auth.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthorizationErrorCodes {

    GENERIC_AUTHORIZATION_ERROR(0, "Auth Generic Error", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_NOT_FOUND(100, "Refresh Token Not Found", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKENS_NOT_FOUND(200, "One Or More Refresh Tokens Not Found", HttpStatus.FORBIDDEN),
    TOKEN_EXPIRED(300, "Token has expired", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(400, "Invalid Token", HttpStatus.FORBIDDEN),
    PERMISSIONS_FOR_GIVEN_AUTH_ROLE_NOT_FOUND(500, "Permissions not found for given role", HttpStatus.FORBIDDEN),
    AUTH_ROLE_FOR_GIVEN_USERNAME_NOT_FOUND(600, "Role not found for given username", HttpStatus.FORBIDDEN),
    USER_AGENT_NOT_FOUND(700, "Could not find user agent", HttpStatus.FORBIDDEN),
    TOKEN_COULD_NOT_BE_EXTRACTED(800, "Token could not be extracted from Cookie header", HttpStatus.FORBIDDEN),
    GRANTED_AUTHORITIES_NOT_FOUND(900, "Could not find granted authorities for user", HttpStatus.FORBIDDEN),
    AUTH_ROLE_NOT_FOUND_FOR_GIVEN_VALUE(1000, "Could not find role for given value", HttpStatus.CONFLICT);

    private final int applicationErrorCode;
    private final String description;
    private final HttpStatus httpStatus;
}
