package com.blueground.units.dao;

import com.blueground.units.converter.UnitDtoConverter;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.repository.UnitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultUnitsDao implements UnitsDao {

    private final UnitsRepository unitsRepository;
    private final UnitDtoConverter unitDtoConverter;

    @Override
    public List<UnitDto> getUnits(String searchValue) {
        return unitsRepository.getUnitsByRegion(searchValue)
                .stream()
                .map(unitDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
