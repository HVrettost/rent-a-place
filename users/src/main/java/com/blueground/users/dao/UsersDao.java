package com.blueground.users.dao;

import com.blueground.users.exception.UsersException;
import com.blueground.users.model.dto.UserDto;

public interface UsersDao {

    UserDto getUserByUsername(String username) throws UsersException;
}
