package com.rentaplace.units.exception;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
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
