package com.rentaplace.reviews.validator;

import com.rentaplace.common.validator.Validator;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.reviews.exception.ReviewsException;
import com.rentaplace.reviews.exception.error.ReviewsErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoValidator implements Validator<ReviewDto> {

    private static final int MINIMUM_SCORE = 1;
    private static final int MAXIMUM_SCORE = 5;

    @Override
    public void validate(ReviewDto reviewDto) throws ReviewsException {
        if (reviewDto.getScore() < MINIMUM_SCORE || reviewDto.getScore() > MAXIMUM_SCORE) {
            throw new ReviewsException(ReviewsErrorCodes.INVALID_SCORE);
        } else if (reviewDto.getUnitId() == null) {
            throw new ReviewsException(ReviewsErrorCodes.UNIT_ID_NOT_FOUND);
        } else if (reviewDto.getUserId() == null) {
            throw new ReviewsException(ReviewsErrorCodes.USER_ID_NOT_FOUND);
        }
    }
}
