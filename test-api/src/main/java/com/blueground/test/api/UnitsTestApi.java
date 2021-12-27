package com.blueground.test.api;

import com.blueground.test.dto.UnitCreationRequestDto;
import com.blueground.units.model.entity.Unit;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "marsrental/test/v1")
public interface UnitsTestApi {

    @DeleteMapping(value = "units/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAllUnits();

    @PostMapping(value = "units",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Unit> createNewUnit(@RequestBody UnitCreationRequestDto dto);

    @GetMapping(value = "units/score", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getUnitScore(@RequestParam("unitId") String unitId);
}
