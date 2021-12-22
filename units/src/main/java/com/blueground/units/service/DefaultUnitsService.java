package com.blueground.units.service;

import com.blueground.units.dao.UnitsDao;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUnitsService implements UnitsService {

    private final UnitsDao unitsDao;

    @Override
    public UnitsResponseDto getUnitsByKeyword(String searchValue, PageReq pageRequest) {
        return unitsDao.getUnits(searchValue, pageRequest);
    }
}
