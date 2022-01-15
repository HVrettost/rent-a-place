package com.blueground.auth.exception;

import com.blueground.common.exception.RentAPlaceCoreException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthException extends RentAPlaceCoreException {

    public AuthException(Throwable throwable) {
        super(throwable);
    }
}
