package com.blueground.common.validator;

import com.blueground.common.exception.MarsRentalCoreException;

public interface Validator<T> {

    void validate(T t) throws MarsRentalCoreException;
}
