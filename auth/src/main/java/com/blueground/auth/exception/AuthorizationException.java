package com.blueground.auth.exception;

import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorizationException extends AuthException {

    private final AuthorizationErrorCodes authorizationErrorCodes;
}
