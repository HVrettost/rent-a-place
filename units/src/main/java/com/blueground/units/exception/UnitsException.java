package com.blueground.units.exception;

import com.blueground.common.exception.RentAPlaceCoreException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import lombok.Getter;

@Getter
public class UnitsException extends RentAPlaceCoreException {

    private final UnitsErrorCodes unitsErrorCodes;

    public UnitsException(UnitsErrorCodes unitsErrorCodes, Throwable throwable) {
        super(throwable);
        this.unitsErrorCodes = unitsErrorCodes;
    }

    public UnitsException(UnitsErrorCodes unitsErrorCodes) {
        this.unitsErrorCodes = unitsErrorCodes;
    }
}
