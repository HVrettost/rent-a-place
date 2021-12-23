package com.blueground.test.controller;

import com.blueground.test.api.UsersTestApi;
import com.blueground.test.repository.UsersTestRepository;
import com.blueground.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersTestController implements UsersTestApi {

    private final UsersTestRepository usersTestRepository;

    @Override
    public ResponseEntity<User> createNewUser() {
        User user = new User();
        user.setName("Haris");
        user.setSurname("Vrettos");
        user.setUsername("vrettost");
        user.setPassword("password");

        return new ResponseEntity<>(usersTestRepository.saveAndFlush(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String userId) {
        return new ResponseEntity<>(usersTestRepository.getById(UUID.fromString(userId)), HttpStatus.OK);
    }
}
