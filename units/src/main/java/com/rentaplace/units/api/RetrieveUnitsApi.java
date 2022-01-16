package com.rentaplace.units.api;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.units.model.dto.UnitDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "rentaplace/v1")
public interface RetrieveUnitsApi {

    @GetMapping(value = "units",
                produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UnitDto>> getUnitsBySearchValue(@RequestParam(value = "search") String searchValue,
                                                        @RequestParam(value = "page") Integer page,
                                                        @RequestParam(value = "pageSize") Integer pageSize) throws RentAPlaceCoreException;

}
