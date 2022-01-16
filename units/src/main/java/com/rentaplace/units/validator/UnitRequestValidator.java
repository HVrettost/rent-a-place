package com.rentaplace.units.validator;

import com.rentaplace.common.validator.Validator;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
import com.rentaplace.units.model.dto.UnitRequestDto;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UnitRequestValidator implements Validator<UnitRequestDto> {

    @Override
    public void validate(UnitRequestDto unitRequestDto) throws UnitsException {
        if (StringUtils.isEmpty(unitRequestDto.description())
                || StringUtils.isBlank(unitRequestDto.region())
                || StringUtils.isEmpty(unitRequestDto.imageUrl())
                || unitRequestDto.price() == null
                || unitRequestDto.cancellationPolicy() == null) {

            throw new UnitsException(UnitsErrorCodes.INVALID_STORE_UNIT_REQUEST);
        }
    }
}
