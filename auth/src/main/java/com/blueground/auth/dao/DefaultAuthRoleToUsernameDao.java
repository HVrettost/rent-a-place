package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.domain.UserAuthType;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.mapper.AuthRoleToUsernameMapper;
import com.blueground.auth.model.entity.AuthRole;
import com.blueground.auth.model.entity.AuthRoleToUsername;
import com.blueground.auth.repository.AuthRoleRepository;
import com.blueground.auth.repository.AuthRoleToUsernameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DefaultAuthRoleToUsernameDao implements AuthRoleToUsernameDao {

    private final AuthRoleToUsernameRepository authRoleToUsernameRepository;
    private final AuthRoleRepository authRoleRepository;
    private final AuthRoleToUsernameMapper authRoleToUsernameMapper;

    @Override
    public AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException {
        AuthRoleToUsername authRoleToUsername = authRoleToUsernameRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.AUTH_ROLE_FOR_GIVEN_USERNAME_NOT_FOUND));
        return authRoleToUsernameMapper.toDto(authRoleToUsername);
    }

    @Override
    @Transactional
    public void saveUserAuthRole(String username, UUID userId, UserAuthType userAuthType) throws AuthorizationException {
        AuthRole authRole = authRoleRepository.findByRole(userAuthType.getAuthRoleValue())
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.AUTH_ROLE_NOT_FOUND_FOR_GIVEN_VALUE));

        authRoleToUsernameRepository.save(createAuthRoleToUsernameEntity(authRole, username, userId));
    }

    private AuthRoleToUsername createAuthRoleToUsernameEntity(AuthRole authRole, String username, UUID userId) {
        AuthRoleToUsername entity = new AuthRoleToUsername();
        entity.setRole(authRole.getRole());
        entity.setRoleId(authRole.getId());
        entity.setUsername(username);
        entity.setUserId(userId);

        return entity;
    }
}
