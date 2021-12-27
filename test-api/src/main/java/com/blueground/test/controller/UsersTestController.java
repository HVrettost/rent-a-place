package com.blueground.test.controller;

import com.blueground.auth.dao.AuthRoleToUsernameDao;
import com.blueground.auth.domain.UserAuthType;
import com.blueground.auth.exception.AuthException;
import com.blueground.test.api.UsersTestApi;
import com.blueground.test.dto.UserCreationRequestDto;
import com.blueground.test.repository.UsersTestRepository;
import com.blueground.users.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersTestController implements UsersTestApi {

    private final UsersTestRepository usersTestRepository;
    private final AuthRoleToUsernameDao authRoleToUsernameDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<User> createNewUser(UserCreationRequestDto requestDto) throws AuthException {
        User user = new User();
        user.setName(requestDto.getName());
        user.setSurname(requestDto.getSurname());
        user.setUsername(requestDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));

        User u = usersTestRepository.saveAndFlush(user);
        authRoleToUsernameDao.saveUserAuthRole(u.getUsername(), u.getUserId(), UserAuthType.SIMPLE);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String userId) {
        return new ResponseEntity<>(usersTestRepository.getById(UUID.fromString(userId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAllUsers() {
        usersTestRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
