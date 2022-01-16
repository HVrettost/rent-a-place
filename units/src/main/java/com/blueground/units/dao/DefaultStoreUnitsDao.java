package com.blueground.units.dao;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.model.dto.UnitRequestDto;
import com.blueground.units.model.entity.Unit;
import com.blueground.units.repository.StoreUnitsRepository;
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
