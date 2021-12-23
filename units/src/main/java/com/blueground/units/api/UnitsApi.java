package com.blueground.units.api;

import com.blueground.units.exception.UnitsException;
import com.blueground.units.model.dto.UnitsResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "marsrental/v1")
public interface UnitsApi {

    @GetMapping(value = "/units",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UnitsResponseDto> getUnitsBySearchKeyword(@RequestParam(value = "search") String searchValue,
                                                             @RequestParam(value = "page") Integer page,
                                                             @RequestParam(value = "pageSize") Integer pageSize) throws UnitsException;
}
