package com.blueground.reviews.validator;

import com.blueground.reviews.domain.ReviewDto;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.reviews.exception.utils.ReviewsExceptionUtils;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoValidator implements Validator<ReviewDto> {

    @Override
    public void validate(ReviewDto reviewDto) throws ReviewsException {
        if (reviewDto.getScore() < 0 || reviewDto.getScore() > 5) {
            throw new ReviewsException(ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.INVALID_SCORE));
        } else if (reviewDto.getUnitId() == null) {
            throw new ReviewsException(ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.UNIT_ID_NOT_FOUND));
        } else if (reviewDto.getUserId() == null) {
            throw new ReviewsException(ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.USER_ID_NOT_FOUND));
        }
    }
}
