package com.blueground.units.validator;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.exception.utils.UnitsExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SearchValueValidator implements Validator<String> {

    @Override
    public void validate(String value) throws UnitsException {
        if (!StringUtils.hasText(value))
            throw new UnitsException(UnitsExceptionUtils.createErrorDetails(UnitsErrorCodes.EMPTY_SEARCH_VALUE));
    }
}
