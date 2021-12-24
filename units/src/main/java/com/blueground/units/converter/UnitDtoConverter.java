package com.blueground.units.converter;

import com.blueground.units.model.entity.Unit;
import com.blueground.units.model.dto.UnitDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitDtoConverter implements Converter<Unit, UnitDto> {

    @Override
    public UnitDto convert(Unit unit) {
        UnitDto unitDto = new UnitDto();
        unitDto.setUnitId(unit.getUnitId());
        unitDto.setTitle(unit.getTitle());
        unitDto.setRegion(unit.getRegion());
        unitDto.setCancellationPolicy(unit.getCancellationPolicy());
        unitDto.setDescription(unit.getDescription());
        unitDto.setImageUrl(unit.getImageUrl());
        unitDto.setPrice(unit.getPrice());
        unitDto.setAverageScore(unit.getAverageScore());

        return unitDto;
    }
}
