package com.rentaplace.units.converter;

import com.rentaplace.units.model.entity.Unit;
import com.rentaplace.units.model.dto.UnitDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnitDtoConverter implements Converter<Unit, UnitDto> {

    @Override
    public UnitDto convert(Unit unit) {
        return new UnitDto(
                unit.getUnitId(),
                unit.getImageUrl(),
                unit.getTitle(),
                unit.getRegion(),
                unit.getDescription(),
                unit.getCancellationPolicy(),
                unit.getPrice(),
                unit.getAverageScore()
        );
    }
}
