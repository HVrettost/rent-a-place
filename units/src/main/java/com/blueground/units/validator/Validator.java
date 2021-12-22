package com.blueground.units.validator;

import com.blueground.units.exception.UnitsException;

public interface Validator<T> {

    void validate(T t) throws UnitsException;
}
