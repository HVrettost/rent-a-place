package com.blueground.test.api;

import com.blueground.test.dto.UnitCreationRequestDto;
import com.blueground.units.model.entity.Unit;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "marsrental/v1/test")
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
