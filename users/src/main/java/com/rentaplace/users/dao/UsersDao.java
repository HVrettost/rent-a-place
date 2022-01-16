package com.rentaplace.users.dao;

import com.rentaplace.users.exception.UsersException;
import com.rentaplace.users.model.domain.UserDomain;

public interface UsersDao {

    UserDomain getUserByUsername(String username) throws UsersException;
}
