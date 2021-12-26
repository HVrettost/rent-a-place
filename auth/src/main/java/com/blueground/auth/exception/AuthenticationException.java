package com.blueground.auth.exception;

import com.blueground.auth.exception.error.AuthenticationErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationException extends AuthException {

    private final AuthenticationErrorCodes authenticationErrorCodes;
}
