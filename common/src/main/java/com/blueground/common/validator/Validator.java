package com.blueground.common.validator;

import com.blueground.common.exception.RentAPlaceCoreException;

public interface Validator<T> {

    void validate(T t) throws RentAPlaceCoreException;
}
