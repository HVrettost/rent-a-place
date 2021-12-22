package com.blueground.common.validator;

public interface Validator<T, S extends Exception> {

    void validate(T t) throws S;
}
