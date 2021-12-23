package com.blueground.test.api;

import com.blueground.users.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "marsrental/v1/test")
public interface UsersTestApi {

    @PostMapping(value = "users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> createNewUser();

    @GetMapping(value = "users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUser(@PathVariable(value = "userId") String userId);
}
