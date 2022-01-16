package com.blueground.units.service;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitRequestDto;

public interface StoreUnitsService {

    void storeUnit(UnitRequestDto unitRequest) throws UnitsException;
}
