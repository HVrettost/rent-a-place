package com.blueground.reviews.converter;

import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.model.entity.Review;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoConverter implements Converter<ReviewDto, Review> {

    @Override
    public Review convert(ReviewDto source) {
        Review review = new Review();
        review.setComment(source.getComment());
        review.setScore(source.getScore());
        review.setUnitId(source.getUnitId());
        review.setUserId(source.getUserId());

        return review;
    }
}
