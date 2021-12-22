package com.blueground.units.service;

import com.blueground.units.dao.UnitsDao;
import com.blueground.units.model.dto.UnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUnitsService implements UnitsService {

    private final UnitsDao unitsDao;

    @Override
    public List<UnitDto> getUnitsByKeyword(String searchValue) {
        return unitsDao.getUnits(searchValue);
    }
}
