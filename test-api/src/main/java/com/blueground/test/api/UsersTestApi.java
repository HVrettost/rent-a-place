package com.blueground.test.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "marsrental/v1/test")
public interface UsersTestApi {

    @PostMapping(name = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createNewUser();
}
