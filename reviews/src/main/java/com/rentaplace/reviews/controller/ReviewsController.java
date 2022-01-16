package com.rentaplace.reviews.controller;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.common.validator.Validator;
import com.rentaplace.reviews.api.ReviewsApi;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.reviews.service.ReviewsService;
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
    public ResponseEntity<Void> saveReview(ReviewDto reviewDto) throws RentAPlaceCoreException {
        reviewDtoValidator.validate(reviewDto);
        reviewsService.saveReviewAndUpdateAverageScoreForUnit(reviewDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
