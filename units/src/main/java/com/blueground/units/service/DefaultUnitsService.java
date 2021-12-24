package com.blueground.units.service;

import com.blueground.units.dao.UnitsDao;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUnitsService implements UnitsService {

    private final UnitsDao unitsDao;

    @Override
    @Transactional(readOnly = true)
    public List<UnitDto> getUnitsBySearchValue(String searchValue, PageReq pageRequest) {
        String lowerCasedSearchValue = searchValue.toLowerCase();

        return unitsDao.getUnitsBySearchValueThatMatchesRegionAndTitle(lowerCasedSearchValue, pageRequest);
    }
}
