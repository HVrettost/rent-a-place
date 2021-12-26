package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.domain.UserAuthType;
import com.blueground.auth.exception.AuthorizationException;

import java.util.UUID;

public interface AuthRoleToUsernameDao {

    AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException;

    void saveUserAuthRole(String username, UUID userId, UserAuthType userAuthType) throws AuthorizationException;
}
