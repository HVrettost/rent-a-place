package com.rentaplace.units.exception.handler;

import com.rentaplace.common.exception.error.ErrorDetails;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.rentaplace.units.controller")
public class UnitsExceptionHandler {

    @ExceptionHandler(value = UnitsException.class)
    public ResponseEntity<ErrorDetails> handleReviewsException(UnitsException uex) {
        log.error("An error occurred with message: " + uex.getUnitsErrorCodes().getDescription(), uex);
        return new ResponseEntity<>(createErrorDetails(uex.getUnitsErrorCodes()), uex.getUnitsErrorCodes().getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        log.error("A serious problem occurred: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(
                createErrorDetails(UnitsErrorCodes.GENERIC_UNITS_ERROR),
                UnitsErrorCodes.GENERIC_UNITS_ERROR.getHttpStatus());
    }

    public ErrorDetails createErrorDetails(UnitsErrorCodes error) {
        return new ErrorDetails(error.getApplicationErrorCode(), error.getDescription());
    }
}
