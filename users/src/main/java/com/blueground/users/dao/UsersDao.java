package com.blueground.users.dao;

import com.blueground.users.exception.UsersException;
import com.blueground.users.model.domain.UserDomain;

public interface UsersDao {

    UserDomain getUserByUsername(String username) throws UsersException;
}
