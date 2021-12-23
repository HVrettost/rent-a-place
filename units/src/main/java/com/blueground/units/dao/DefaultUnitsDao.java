package com.blueground.units.dao;

import com.blueground.units.converter.UnitDtoConverter;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.model.dto.UnitsResponseDto;
import com.blueground.units.model.entity.Unit;
import com.blueground.units.repository.UnitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultUnitsDao implements UnitsDao {

    private static final String SORTING_VALUE = "averageScore";

    private final UnitsRepository unitsRepository;
    private final UnitDtoConverter unitDtoConverter;

    @Override
    public UnitsResponseDto getUnits(String searchValue, PageReq pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(),
                pageRequest.getPageSize(), Sort.by(Sort.Order.desc(SORTING_VALUE)));
        Page<Unit> unitsPage =  unitsRepository.findUnitsBySearchValue(searchValue, pageable);

        List<UnitDto> units = unitsPage.getContent()
                .stream()
                .map(unitDtoConverter::convert)
                .collect(Collectors.toList());

        return new UnitsResponseDto(units, unitsPage.getTotalElements(), unitsPage.getTotalPages());
    }
}
