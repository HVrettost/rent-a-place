package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToAuthoritiesDomain;
import com.blueground.auth.exception.AuthorizationException;

public interface AuthRoleToPermissionsDao {

    AuthRoleToAuthoritiesDomain getPermissionsByAuthRole(String authRole) throws AuthorizationException;
}
