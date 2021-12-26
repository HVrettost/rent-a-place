package com.blueground.users.exception;

import com.blueground.users.exception.error.UsersErrorCodes;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersException extends Exception {

    private final UsersErrorCodes usersErrorCodes;

}
