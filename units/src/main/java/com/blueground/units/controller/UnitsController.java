package com.blueground.units.controller;

import com.blueground.units.api.UnitsApi;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.domain.PageReq;
import com.blueground.units.model.dto.UnitsResponseDto;
import com.blueground.units.service.UnitsService;
import com.blueground.units.validator.PageRequestValidator;
import com.blueground.units.validator.SearchValueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UnitsController implements UnitsApi {

    private final UnitsService unitsService;
    private final SearchValueValidator searchValueValidator;
    private final PageRequestValidator pageRequestValidator;

    @Override
    public ResponseEntity<UnitsResponseDto> getUnitsBySearchKeyword(String searchValue, Integer page, Integer pageSize) throws UnitsException {
        searchValueValidator.validate(searchValue);
        PageReq pageRequest = createPageRequest(page, pageSize);
        pageRequestValidator.validate(pageRequest);
        UnitsResponseDto response = unitsService.getUnitsByKeyword(searchValue, pageRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PageReq createPageRequest(Integer page, Integer pageSize) {
        return new PageReq(page, pageSize);
    }
}
