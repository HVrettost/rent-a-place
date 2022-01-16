package com.rentaplace.auth.dao;

import com.rentaplace.auth.domain.AuthRoleToAuthoritiesDomain;
import com.rentaplace.auth.exception.AuthorizationException;
import com.rentaplace.auth.exception.error.AuthorizationErrorCodes;
import com.rentaplace.auth.model.entity.AuthRoleToAuthorities;
import com.rentaplace.auth.repository.AuthRoleToAuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultAuthRoleToPermissionsDao implements AuthRoleToPermissionsDao {

    private final AuthRoleToAuthoritiesRepository authRoleToAuthoritiesRepository;
    private final Converter<AuthRoleToAuthorities, AuthRoleToAuthoritiesDomain> converter;

    @Override
    public AuthRoleToAuthoritiesDomain getPermissionsByAuthRole(String authRole) throws AuthorizationException {
        AuthRoleToAuthorities authRoleToPermissions = authRoleToAuthoritiesRepository.findByRole(authRole)
                .orElseThrow(() -> new AuthorizationException(AuthorizationErrorCodes.PERMISSIONS_FOR_GIVEN_AUTH_ROLE_NOT_FOUND));
        return converter.convert(authRoleToPermissions);
    }
}
