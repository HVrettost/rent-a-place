package com.blueground.auth.exception;

import com.blueground.common.exception.MarsRentalCoreException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthException extends MarsRentalCoreException {

    public AuthException(Throwable throwable) {
        super(throwable);
    }
}
