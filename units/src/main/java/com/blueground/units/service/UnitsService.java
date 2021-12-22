package com.blueground.units.service;

import com.blueground.units.model.dto.UnitDto;

import java.util.List;

public interface UnitsService {

    List<UnitDto> getUnitsByKeyword(String searchValue);
}
