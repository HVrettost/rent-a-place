package com.rentaplace.units.api;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.units.model.dto.UnitRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "rentaplace/v1")
public interface StoreUnitsApi {

    @PreAuthorize("hasAuthority('STORE_UNITS')")
    @PostMapping(value = "units",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> storeUnit(@RequestBody UnitRequestDto unitRequest) throws RentAPlaceCoreException;
}
