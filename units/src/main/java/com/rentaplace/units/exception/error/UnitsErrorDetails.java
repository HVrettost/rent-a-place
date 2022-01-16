package com.rentaplace.units.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class UnitsErrorDetails {

    private final int applicationErrorCode;
    private final String description;
    @JsonIgnore
    private final HttpStatus httpStatus;
}
