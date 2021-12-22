package com.blueground.units.exception.handler;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.blueground.units.controller")
public class UnitsExceptionHandler {

    @ExceptionHandler(value = UnitsException.class)
    public ResponseEntity<UnitsErrorDetails> handleReviewsException(UnitsException uex) {
        return new ResponseEntity<>(uex.getErrorDetails(), uex.getErrorDetails().getHttpStatus());
    }
}
