package com.blueground.units.service;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.model.dto.UnitsResponseDto;

import java.util.List;

public interface UnitsService {

    List<UnitDto> getUnitsBySearchValue(String searchValue, PageReq pageRequest);
}
