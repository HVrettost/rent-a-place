package com.rentaplace.auth.dao;

import com.rentaplace.auth.domain.AuthRoleToUsernameDomain;
import com.rentaplace.auth.domain.UserAuthType;
import com.rentaplace.auth.exception.AuthorizationException;
import com.rentaplace.auth.exception.error.AuthorizationErrorCodes;
import com.rentaplace.auth.model.entity.AuthRole;
import com.rentaplace.auth.model.entity.AuthRoleToUsername;
import com.rentaplace.auth.repository.AuthRoleRepository;
import com.rentaplace.auth.repository.AuthRoleToUsernameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DefaultAuthRoleToUsernameDao implements AuthRoleToUsernameDao {

    private final AuthRoleToUsernameRepository authRoleToUsernameRepository;
    private final AuthRoleRepository authRoleRepository;
    private final Converter<AuthRoleToUsername, AuthRoleToUsernameDomain> converter;

    @Override
    public AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException {
        AuthRoleToUsername authRoleToUsername = authRoleToUsernameRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.AUTH_ROLE_FOR_GIVEN_USERNAME_NOT_FOUND));
        return converter.convert(authRoleToUsername);
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
