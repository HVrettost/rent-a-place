package com.rentaplace.units.validator;

import com.rentaplace.common.validator.Validator;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SearchValueValidator implements Validator<String> {

    @Override
    public void validate(String value) throws UnitsException {
        if (!StringUtils.hasText(value)) {
            throw new UnitsException(UnitsErrorCodes.EMPTY_SEARCH_VALUE);
        }
    }
}
