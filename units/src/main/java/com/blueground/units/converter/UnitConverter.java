package com.blueground.units.converter;

import com.blueground.units.model.dto.UnitRequestDto;
import com.blueground.units.model.entity.Unit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnitConverter implements Converter<UnitRequestDto, Unit> {

    private final ModelMapper modelMapper;

    @Override
    public Unit convert(@NonNull UnitRequestDto unitRequest) {
        return modelMapper.map(unitRequest, Unit.class);
    }
}
