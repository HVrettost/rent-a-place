package com.rentaplace.auth.exception.handler;

import com.rentaplace.auth.exception.AuthenticationException;
import com.rentaplace.auth.exception.AuthorizationException;
import com.rentaplace.auth.exception.error.AuthenticationErrorCodes;
import com.rentaplace.auth.exception.error.AuthorizationErrorCodes;
import com.rentaplace.common.exception.error.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(value = "com.rentaplace.auth.controller")
public class AuthExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetails> handleAuthenticationException(AuthenticationException authenticationEx) {
        log.error("An error occurred with message: {}", authenticationEx.getAuthenticationErrorCodes().getDescription(), authenticationEx);
        return new ResponseEntity<>(createErrorDetails(authenticationEx.getAuthenticationErrorCodes()), authenticationEx.getAuthenticationErrorCodes().getHttpStatus());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorDetails> handleAuthorizationException(AuthorizationException authorizationEx) {
        log.error("An error occurred with message: {}", authorizationEx.getAuthorizationErrorCodes().getDescription(), authorizationEx);
        return new ResponseEntity<>(createErrorDetails(authorizationEx.getAuthorizationErrorCodes()), authorizationEx.getAuthorizationErrorCodes().getHttpStatus());
    }

    @ExceptionHandler({ BadCredentialsException.class, UsernameNotFoundException.class })
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException bcex) {
        log.error("An error occurred with message: {}", bcex.getMessage(), bcex);
        return new ResponseEntity<>(createErrorDetails(AuthenticationErrorCodes.BAD_CREDENTIALS), AuthenticationErrorCodes.BAD_CREDENTIALS.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        log.error("An error occurred with message: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorDetails(AuthenticationErrorCodes.GENERIC_AUTHENTICATION_ERROR), AuthenticationErrorCodes.GENERIC_AUTHENTICATION_ERROR.getHttpStatus());
    }

    public ErrorDetails createErrorDetails(AuthenticationErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }

    public ErrorDetails createErrorDetails(AuthorizationErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }
}
