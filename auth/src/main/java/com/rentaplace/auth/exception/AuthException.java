package com.rentaplace.auth.exception;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthException extends RentAPlaceCoreException {

    public AuthException(Throwable throwable) {
        super(throwable);
    }
}
