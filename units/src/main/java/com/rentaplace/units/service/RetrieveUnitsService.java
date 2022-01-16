package com.rentaplace.units.service;

import com.rentaplace.units.model.domain.PageReq;
import com.rentaplace.units.model.dto.UnitDto;

import java.util.List;

public interface RetrieveUnitsService {

    List<UnitDto> getUnitsByTitleAnRegion(String searchValue, PageReq pageRequest);
}
