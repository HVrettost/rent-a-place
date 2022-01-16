package com.rentaplace.test.controller;

import com.rentaplace.auth.dao.AuthRoleToUsernameDao;
import com.rentaplace.auth.domain.UserAuthType;
import com.rentaplace.auth.exception.AuthException;
import com.rentaplace.test.api.UsersTestApi;
import com.rentaplace.test.dto.UserCreationRequestDto;
import com.rentaplace.test.repository.UsersTestRepository;
import com.rentaplace.users.model.entity.User;
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
        UserAuthType userAuthType = null;

        if (UserAuthType.SUBSCRIBEDSIMPLE.getAuthRoleValue().equals(requestDto.getUserAuthType())) {
            userAuthType = UserAuthType.SUBSCRIBEDSIMPLE;
        } else if (UserAuthType.SUBSCRIBEDPREMIUM.getAuthRoleValue().equals(requestDto.getUserAuthType())) {
            userAuthType = UserAuthType.SUBSCRIBEDPREMIUM;
        } else if (UserAuthType.ADMIN.getAuthRoleValue().equals(requestDto.getUserAuthType())) {
            userAuthType = UserAuthType.SUBSCRIBEDSIMPLE;
        }

        authRoleToUsernameDao.saveUserAuthRole(u.getUsername(), u.getUserId(), userAuthType);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String userId) {
        return new ResponseEntity<>(usersTestRepository.getById(UUID.fromString(userId)), HttpStatus.OK);
    }
}
