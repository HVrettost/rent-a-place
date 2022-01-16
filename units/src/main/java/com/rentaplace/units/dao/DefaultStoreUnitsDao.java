package com.rentaplace.units.dao;

import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
import com.rentaplace.units.model.dto.UnitRequestDto;
import com.rentaplace.units.model.entity.Unit;
import com.rentaplace.units.repository.StoreUnitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultStoreUnitsDao implements StoreUnitsDao {

    private final StoreUnitsRepository storeUnitsRepository;
    private final Converter<UnitRequestDto, Unit> converter;

    @Override
    public void storeUnit(UnitRequestDto unitRequest) throws UnitsException {
        Unit unit = Optional.ofNullable(converter.convert(unitRequest))
                .orElseThrow(() -> new UnitsException(UnitsErrorCodes.GENERIC_UNITS_ERROR));

        storeUnitsRepository.save(unit);
    }
}
