package com.rentaplace.units.service;

import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.model.dto.UnitRequestDto;

public interface StoreUnitsService {

    void storeUnit(UnitRequestDto unitRequest) throws UnitsException;
}
