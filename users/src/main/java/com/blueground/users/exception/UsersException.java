package com.blueground.users.exception;

import com.blueground.common.exception.MarsRentalCoreException;
import com.blueground.users.exception.error.UsersErrorCodes;
import lombok.Getter;

@Getter
public class UsersException extends MarsRentalCoreException {

    private final UsersErrorCodes usersErrorCodes;

    public UsersException(UsersErrorCodes usersErrorCodes, Throwable throwable) {
        super(throwable);
        this.usersErrorCodes = usersErrorCodes;
    }

    public UsersException(UsersErrorCodes usersErrorCodes) {
        this.usersErrorCodes = usersErrorCodes;
    }
}
