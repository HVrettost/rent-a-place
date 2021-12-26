package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.mapper.AuthRoleToUsernameMapper;
import com.blueground.auth.model.entity.AuthRoleToUsername;
import com.blueground.auth.repository.AuthRoleToUsernameRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthRoleToUsernameDao implements AuthRoleToUsernameDao {

    private final AuthRoleToUsernameRepository authRoleToUsernameRepository;
    private final AuthRoleToUsernameMapper authRoleToUsernameMapper;

    public DefaultAuthRoleToUsernameDao(AuthRoleToUsernameRepository authRoleToUsernameRepository,
                                        AuthRoleToUsernameMapper authRoleToUsernameMapper) {
        this.authRoleToUsernameRepository = authRoleToUsernameRepository;
        this.authRoleToUsernameMapper = authRoleToUsernameMapper;
    }

    @Override
    public AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException {
        AuthRoleToUsername authRoleToUsername = authRoleToUsernameRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.AUTH_ROLE_FOR_GIVEN_USERNAME_NOT_FOUND));
        return authRoleToUsernameMapper.toDto(authRoleToUsername);
    }
}
