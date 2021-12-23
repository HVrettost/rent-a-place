package com.blueground.units.service;

import com.blueground.units.dao.UnitsDao;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUnitsService implements UnitsService {

    private final UnitsDao unitsDao;

    @Override
    @Transactional(readOnly = true)
    public UnitsResponseDto getUnitsByKeyword(String searchValue, PageReq pageRequest) {
        String lowerCasedSearchValue = searchValue.toLowerCase();
        return unitsDao.getUnits(lowerCasedSearchValue, pageRequest);
    }
}
