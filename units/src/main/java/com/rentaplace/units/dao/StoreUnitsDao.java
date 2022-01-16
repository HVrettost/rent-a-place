package com.rentaplace.units.dao;

import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.model.dto.UnitRequestDto;

public interface StoreUnitsDao {

    void storeUnit(UnitRequestDto unitRequest) throws UnitsException;
}
