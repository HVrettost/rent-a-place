package com.blueground.units.service;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitsResponseDto;

public interface UnitsService {

    UnitsResponseDto getUnitsByKeyword(String searchValue, PageReq pageRequest);
}
