package com.blueground.units.dao;

import com.blueground.units.converter.UnitDtoConverter;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.model.entity.Unit;
import com.blueground.units.repository.UnitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultUnitsDao implements UnitsDao {

    private final UnitsRepository unitsRepository;
    private final UnitDtoConverter unitDtoConverter;

    @Override
    public List<UnitDto> getUnitsBySearchValueThatMatchesRegionAndTitle(String searchValue, PageReq pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(),
                pageRequest.getPageSize());
        Slice<Unit> unitsPage =  unitsRepository.findUnitsByVectorizedValues(searchValue, pageable);

        return unitsPage.getContent()
                .stream()
                .map(unitDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
