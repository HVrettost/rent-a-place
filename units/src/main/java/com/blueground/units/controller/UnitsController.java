package com.blueground.units.controller;

import com.blueground.common.exception.RentAPlaceCoreException;
import com.blueground.common.validator.Validator;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitDto;
import com.blueground.units.api.UnitsApi;
import com.blueground.units.service.UnitsService;
import com.blueground.units.validator.SearchValueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UnitsController implements UnitsApi {

    private final UnitsService unitsService;
    private final SearchValueValidator searchValueValidator;
    private final Validator<PageReq> pageRequestValidator;

    @Override
    public ResponseEntity<List<UnitDto>> getUnitsBySearchValue(String searchValue, Integer page, Integer pageSize) throws RentAPlaceCoreException {
        searchValueValidator.validate(searchValue);
        PageReq pageRequest = createPageRequest(page, pageSize);
        pageRequestValidator.validate(pageRequest);
        List<UnitDto> units = unitsService.getUnitsByTitleAnRegion(searchValue, pageRequest);

        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    private PageReq createPageRequest(Integer page, Integer pageSize) {
        return new PageReq(page, pageSize);
    }
}
