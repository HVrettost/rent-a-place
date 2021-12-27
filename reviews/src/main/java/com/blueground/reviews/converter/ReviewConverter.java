package com.blueground.reviews.converter;

import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.model.entity.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewConverter implements Converter<ReviewDto, Review> {

    private final ModelMapper modelMapper;

    @Override
    public Review convert(ReviewDto source) {
        return modelMapper.map(source, Review.class);
    }
}
