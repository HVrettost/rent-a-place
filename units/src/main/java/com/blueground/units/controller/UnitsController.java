package com.blueground.units.controller;

import com.blueground.units.api.UnitsApi;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitDto;
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

    @Override
    public ResponseEntity<List<UnitDto>> getUnitsBySearchKeyword(String searchValue) throws UnitsException {
        searchValueValidator.validate(searchValue);
        List<UnitDto> units = unitsService.getUnitsByKeyword(searchValue);

        return new ResponseEntity<>(units, HttpStatus.OK);
    }
}
