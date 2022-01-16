package com.rentaplace.units.dao;

import com.rentaplace.units.model.domain.PageReq;
import com.rentaplace.units.model.dto.UnitDto;
import com.rentaplace.units.model.entity.Unit;
import com.rentaplace.units.repository.RetrieveUnitsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultRetrieveUnitsDao implements RetrieveUnitsDao {

    private final RetrieveUnitsRepository unitsRepository;
    private final Converter<Unit, UnitDto> converter;

    @Override
    public List<UnitDto> getUnitsBySearchValueFromTokens(String searchValue, PageReq pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getPageSize());
        Slice<Unit> unitsPage =  unitsRepository.findUnitsByVectorizedValues(prepareSearchValueForQuery(searchValue), pageable);

        return unitsPage.getContent()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    private String prepareSearchValueForQuery(String searchValue) {
        return searchValue.trim().replace(" ", " & ");
    }
}
