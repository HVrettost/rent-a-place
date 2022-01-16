package com.blueground.units.dao;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitRequestDto;

public interface StoreUnitsDao {

    void storeUnit(UnitRequestDto unitRequest) throws UnitsException;
}
