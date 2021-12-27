package com.blueground.units.converter;

import com.blueground.units.model.entity.Unit;
import com.blueground.units.model.dto.UnitDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnitDtoConverter implements Converter<Unit, UnitDto> {

    private final ModelMapper modelMapper;

    @Override
    public UnitDto convert(Unit unit) {
        return modelMapper.map(unit, UnitDto.class);
    }
}
