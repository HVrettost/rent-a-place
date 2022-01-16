package com.rentaplace.test.api;

import com.rentaplace.auth.exception.AuthException;
import com.rentaplace.test.dto.UserCreationRequestDto;
import com.rentaplace.users.model.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping(value = "rentaplace/test/v1/users")
public interface UsersTestApi {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> createNewUser(@RequestBody UserCreationRequestDto requestDto) throws AuthException;

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUser(@PathVariable(value = "userId") String userId);
}
