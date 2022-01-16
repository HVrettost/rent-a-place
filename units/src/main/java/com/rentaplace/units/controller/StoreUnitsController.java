package com.rentaplace.units.controller;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.units.api.StoreUnitsApi;
import com.rentaplace.units.model.dto.UnitRequestDto;
import com.rentaplace.units.service.StoreUnitsService;
import com.rentaplace.units.validator.UnitRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreUnitsController implements StoreUnitsApi {

    private final StoreUnitsService storeUnitsService;
    private final UnitRequestValidator unitRequestValidator;

    @Override
    public ResponseEntity<Void> storeUnit(UnitRequestDto unitRequest) throws RentAPlaceCoreException {
        unitRequestValidator.validate(unitRequest);
        storeUnitsService.storeUnit(unitRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
