package com.blueground.units.service;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.dao.RetrieveUnitsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultRetrieveUnitsService implements RetrieveUnitsService {

    private final RetrieveUnitsDao unitsDao;

    @Override
    @Transactional(readOnly = true)
    public List<UnitDto> getUnitsByTitleAnRegion(String searchValue, PageReq pageRequest) {
        String lowerCasedSearchValue = searchValue.toLowerCase();

        return unitsDao.getUnitsBySearchValueFromTokens(lowerCasedSearchValue, pageRequest);
    }
}
