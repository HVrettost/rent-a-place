package com.blueground.auth.exception;

import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import lombok.Getter;

@Getter
public class AuthorizationException extends AuthException {

    private final AuthorizationErrorCodes authorizationErrorCodes;

    public AuthorizationException(AuthorizationErrorCodes authorizationErrorCodes, Throwable throwable) {
        super(throwable);
        this.authorizationErrorCodes = authorizationErrorCodes;
    }

    public AuthorizationException(AuthorizationErrorCodes authorizationErrorCodes) {
        this.authorizationErrorCodes = authorizationErrorCodes;
    }
}
