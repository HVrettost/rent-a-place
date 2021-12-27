package com.blueground.test.api;

import com.blueground.auth.exception.AuthException;
import com.blueground.test.dto.UserCreationRequestDto;
import com.blueground.users.model.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "marsrental/test/v1/users")
public interface UsersTestApi {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> createNewUser(@RequestBody UserCreationRequestDto requestDto) throws AuthException;

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUser(@PathVariable(value = "userId") String userId);

    @DeleteMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAllUsers();
}
