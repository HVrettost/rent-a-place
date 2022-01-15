package com.blueground.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RentAPlaceCoreException extends Exception {

    public RentAPlaceCoreException(Throwable throwable) {
        super(throwable);
    }
}
