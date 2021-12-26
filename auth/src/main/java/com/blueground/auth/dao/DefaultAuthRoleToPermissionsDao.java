package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToAuthoritiesDomain;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.mapper.AuthRoleToAuthoritiesMapper;
import com.blueground.auth.model.entity.AuthRoleToAuthorities;
import com.blueground.auth.repository.AuthRoleToAuthoritiesRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthRoleToPermissionsDao implements AuthRoleToPermissionsDao {

    private final AuthRoleToAuthoritiesRepository authRoleToAuthoritiesRepository;
    private final AuthRoleToAuthoritiesMapper authRoleToAuthoritiesMapper;

    public DefaultAuthRoleToPermissionsDao(AuthRoleToAuthoritiesRepository authRoleToAuthoritiesRepository,
                                           AuthRoleToAuthoritiesMapper authRoleToAuthoritiesMapper) {
        this.authRoleToAuthoritiesRepository = authRoleToAuthoritiesRepository;
        this.authRoleToAuthoritiesMapper = authRoleToAuthoritiesMapper;
    }

    @Override
    public AuthRoleToAuthoritiesDomain getPermissionsByAuthRole(String authRole) throws AuthorizationException {
        AuthRoleToAuthorities authRoleToPermissions = authRoleToAuthoritiesRepository.findByRole(authRole)
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.PERMISSIONS_FOR_GIVEN_AUTH_ROLE_NOT_FOUND));
        return authRoleToAuthoritiesMapper.toDto(authRoleToPermissions);
    }
}
