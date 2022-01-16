package com.rentaplace.units.service;

import com.rentaplace.units.dao.StoreUnitsDao;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.model.dto.UnitRequestDto;
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
