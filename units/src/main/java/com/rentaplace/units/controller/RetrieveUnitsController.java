package com.rentaplace.units.controller;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.common.validator.Validator;
import com.rentaplace.units.model.domain.PageReq;
import com.rentaplace.units.model.dto.UnitDto;
import com.rentaplace.units.api.RetrieveUnitsApi;
import com.rentaplace.units.service.RetrieveUnitsService;
import com.rentaplace.units.validator.SearchValueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RetrieveUnitsController implements RetrieveUnitsApi {

    private final RetrieveUnitsService retrieveUnitsService;
    private final SearchValueValidator searchValueValidator;
    private final Validator<PageReq> pageRequestValidator;

    @Override
    public ResponseEntity<List<UnitDto>> getUnitsBySearchValue(String searchValue, Integer page, Integer pageSize) throws RentAPlaceCoreException {
        searchValueValidator.validate(searchValue);
        PageReq pageRequest = createPageRequest(page, pageSize);
        pageRequestValidator.validate(pageRequest);
        List<UnitDto> units = retrieveUnitsService.getUnitsByTitleAnRegion(searchValue, pageRequest);

        return new ResponseEntity<>(units, HttpStatus.OK);
    }


    private PageReq createPageRequest(Integer page, Integer pageSize) {
        return new PageReq(page, pageSize);
    }
}
