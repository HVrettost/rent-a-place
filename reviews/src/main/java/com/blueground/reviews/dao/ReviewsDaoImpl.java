package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.model.entity.Review;
import com.blueground.reviews.repository.ReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewsDaoImpl implements ReviewsDao {

    private final ReviewsRepository reviewsRepository;
    private final Converter<ReviewDto, Review> reviewDtoConverter;

    @Override
    @Transactional
    public void storeReview(ReviewDto review) throws ReviewsException {
        Review entity = Optional.ofNullable(reviewDtoConverter.convert(review))
                .orElseThrow(ReviewsException::new);
        reviewsRepository.save(entity);
    }
}
