package com.rentaplace.auth.dao;

import com.rentaplace.auth.domain.AuthRoleToUsernameDomain;
import com.rentaplace.auth.domain.UserAuthType;
import com.rentaplace.auth.exception.AuthorizationException;

import java.util.UUID;

public interface AuthRoleToUsernameDao {

    AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException;

    void saveUserAuthRole(String username, UUID userId, UserAuthType userAuthType) throws AuthorizationException;
}
