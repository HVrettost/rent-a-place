package com.blueground.units.service;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;

import java.util.List;

public interface RetrieveUnitsService {

    List<UnitDto> getUnitsByTitleAnRegion(String searchValue, PageReq pageRequest);
}
