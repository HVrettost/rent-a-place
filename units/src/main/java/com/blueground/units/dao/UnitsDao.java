package com.blueground.units.dao;

import com.blueground.units.model.dto.UnitDto;

import java.util.List;

public interface UnitsDao {

    List<UnitDto> getUnits(String searchValue);
}
