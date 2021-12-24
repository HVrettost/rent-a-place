package com.blueground.units.dao;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;

import java.util.List;

public interface UnitsDao {

    List<UnitDto> getUnitsBySearchValueThatMatchesRegionAndTitle(String searchValue, PageReq pageRequest);
}
