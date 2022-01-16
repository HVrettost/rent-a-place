package com.rentaplace.reviews.validator;

import com.rentaplace.reviews.exception.ReviewsException;

public interface Validator<T> {

    void validate(T t) throws ReviewsException;
}
