package com.blueground.units.dao;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitsResponseDto;

public interface UnitsDao {

    UnitsResponseDto getUnits(String searchValue, PageReq pageRequest);
}
