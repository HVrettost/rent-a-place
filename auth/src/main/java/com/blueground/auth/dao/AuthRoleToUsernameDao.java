package com.blueground.auth.dao;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.exception.AuthorizationException;

public interface AuthRoleToUsernameDao {

    AuthRoleToUsernameDomain getAuthRoleByUsername(String username) throws AuthorizationException;
}
