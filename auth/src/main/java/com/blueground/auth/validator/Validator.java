package com.blueground.auth.validator;

import com.blueground.auth.exception.AuthException;

public interface Validator<T> {

    void validate(T t) throws AuthException;
}
