package com.rentaplace.common.validator;

import com.rentaplace.common.exception.RentAPlaceCoreException;

public interface Validator<T> {

    void validate(T t) throws RentAPlaceCoreException;
}
