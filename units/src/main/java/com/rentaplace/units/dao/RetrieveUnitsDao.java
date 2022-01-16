package com.rentaplace.units.dao;

import com.rentaplace.units.model.domain.PageReq;
import com.rentaplace.units.model.dto.UnitDto;

import java.util.List;

public interface RetrieveUnitsDao {

    List<UnitDto> getUnitsBySearchValueFromTokens(String searchValue, PageReq pageRequest);
}
