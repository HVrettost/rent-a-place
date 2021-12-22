package com.blueground.test.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "marsrental/v1/test")
public interface UnitsTestApi {

    @DeleteMapping(name = "/units/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAllUnits();

    @PostMapping(name = "/units",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createNewUnit();
}
