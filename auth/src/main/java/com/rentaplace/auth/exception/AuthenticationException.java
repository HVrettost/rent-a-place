package com.rentaplace.auth.exception;

import com.rentaplace.auth.exception.error.AuthenticationErrorCodes;
import lombok.Getter;

@Getter
public class AuthenticationException extends AuthException {

    private final AuthenticationErrorCodes authenticationErrorCodes;

    public AuthenticationException(AuthenticationErrorCodes authenticationErrorCodes, Throwable throwable) {
        super(throwable);
        this.authenticationErrorCodes = authenticationErrorCodes;
    }

    public AuthenticationException(AuthenticationErrorCodes authenticationErrorCodes) {
        this.authenticationErrorCodes = authenticationErrorCodes;
    }
}
