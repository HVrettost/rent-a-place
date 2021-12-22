package com.blueground.units.exception.handler;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.exception.error.UnitsErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.blueground.units.controller")
public class UnitsExceptionHandler {

    @ExceptionHandler(value = UnitsException.class)
    public ResponseEntity<UnitsErrorDetails> handleReviewsException(UnitsException uex) {
        return new ResponseEntity<>(uex.getErrorDetails(), uex.getErrorDetails().getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<UnitsErrorDetails> handleGenericException(Exception ex) {
        log.error("A serious problem occurred: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(
                new UnitsErrorDetails(
                        UnitsErrorCodes.GENERIC_UNITS_ERROR.getApplicationErrorCode(),
                        UnitsErrorCodes.GENERIC_UNITS_ERROR.getDescription(),
                        UnitsErrorCodes.GENERIC_UNITS_ERROR.getHttpStatus()),
                UnitsErrorCodes.GENERIC_UNITS_ERROR.getHttpStatus());
    }
}
