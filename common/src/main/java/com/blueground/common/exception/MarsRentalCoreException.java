package com.blueground.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MarsRentalCoreException extends Exception {

    public MarsRentalCoreException(Throwable throwable) {
        super(throwable);
    }
}
