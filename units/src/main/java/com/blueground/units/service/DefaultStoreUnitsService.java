package com.blueground.units.service;

import com.blueground.units.dao.StoreUnitsDao;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultStoreUnitsService implements StoreUnitsService {

    private final StoreUnitsDao storeUnitsDao;

    @Override
    public void storeUnit(UnitRequestDto unitRequest) throws UnitsException {
        storeUnitsDao.storeUnit(unitRequest);
    }
}
