package com.blueground.units.exception;

import com.blueground.units.exception.error.UnitsErrorDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnitsException extends Exception {

    private final UnitsErrorDetails errorDetails;
}
