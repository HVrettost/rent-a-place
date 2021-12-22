package com.blueground.units.exception.utils;

import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.exception.error.UnitsErrorDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnitsExceptionUtils {

    public UnitsErrorDetails createErrorDetails(UnitsErrorCodes error) {
        return new UnitsErrorDetails(error.getApplicationErrorCode(), error.getDescription(), error.getHttpStatus());
    }
}
