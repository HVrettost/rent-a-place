package com.blueground.reviews.controller;

import com.blueground.reviews.api.ReviewsApi;
import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.service.ReviewsService;
import com.blueground.reviews.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewsController implements ReviewsApi {

    private final ReviewsService reviewsService;
    private final Validator<ReviewDto> reviewDtoValidator;

    @Override
    public ResponseEntity<Void> saveReview(ReviewDto reviewDto) throws ReviewsException {
        reviewDtoValidator.validate(reviewDto);
        reviewsService.saveReview(reviewDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
