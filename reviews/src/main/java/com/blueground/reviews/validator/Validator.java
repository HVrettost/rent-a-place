package com.blueground.reviews.validator;

import com.blueground.reviews.exception.ReviewsException;

public interface Validator<T> {

    void validate(T t) throws ReviewsException;
}
