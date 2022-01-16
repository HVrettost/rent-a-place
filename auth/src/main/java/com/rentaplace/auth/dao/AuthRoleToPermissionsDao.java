package com.rentaplace.auth.dao;

import com.rentaplace.auth.domain.AuthRoleToAuthoritiesDomain;
import com.rentaplace.auth.exception.AuthorizationException;

public interface AuthRoleToPermissionsDao {

    AuthRoleToAuthoritiesDomain getPermissionsByAuthRole(String authRole) throws AuthorizationException;
}
