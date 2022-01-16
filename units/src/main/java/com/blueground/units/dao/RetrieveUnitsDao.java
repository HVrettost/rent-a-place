package com.blueground.units.dao;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;

import java.util.List;

public interface RetrieveUnitsDao {

    List<UnitDto> getUnitsBySearchValueFromTokens(String searchValue, PageReq pageRequest);
}
