package com.blueground.auth.exception.handler;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthenticationErrorCodes;
import com.blueground.common.exception.error.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(value = "com.blueground.auth.controller")
public class AuthExceptionHandler {

    @ExceptionHandler({ AuthenticationException.class, AuthorizationException.class})
    public ResponseEntity<ErrorDetails> handleAuthenticationException(AuthenticationException rex) {
        return new ResponseEntity<>(createErrorDetails(rex.getAuthenticationErrorCodes()), rex.getAuthenticationErrorCodes().getHttpStatus());
    }

    @ExceptionHandler({ BadCredentialsException.class, UsernameNotFoundException.class })
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException bcex) {
        log.error(bcex.getMessage(), bcex);
        return new ResponseEntity<>(createErrorDetails(AuthenticationErrorCodes.BAD_CREDENTIALS), AuthenticationErrorCodes.BAD_CREDENTIALS.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorDetails(AuthenticationErrorCodes.GENERIC_AUTHENTICATION_ERROR), AuthenticationErrorCodes.GENERIC_AUTHENTICATION_ERROR.getHttpStatus());
    }

    public ErrorDetails createErrorDetails(AuthenticationErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }
}
