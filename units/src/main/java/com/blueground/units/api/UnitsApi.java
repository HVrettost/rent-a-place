package com.blueground.units.api;

import com.blueground.common.exception.MarsRentalCoreException;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "marsrental/v1")
public interface UnitsApi {

    @PreAuthorize("hasAuthority('RETRIEVE_UNITS')")
    @GetMapping(value = "units",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UnitDto>> getUnitsBySearchValue(@RequestParam(value = "search") String searchValue,
                                                        @RequestParam(value = "page") Integer page,
                                                        @RequestParam(value = "pageSize") Integer pageSize) throws UnitsException, MarsRentalCoreException;
}
