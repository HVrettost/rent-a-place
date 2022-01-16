package com.rentaplace.auth.validator;

import com.rentaplace.auth.exception.AuthException;

public interface Validator<T> {

    void validate(T t) throws AuthException;
}
