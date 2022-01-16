package com.rentaplace.test.controller;

import com.rentaplace.reviews.model.entity.Review;
import com.rentaplace.test.api.ReviewsTestApi;
import com.rentaplace.test.repository.ReviewsTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewsTestController implements ReviewsTestApi {

    private final ReviewsTestRepository reviewsTestRepository;

    @Override
    public ResponseEntity<Void> deleteAllReviews() {
        reviewsTestRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Integer> getScoreAverageForUnit(String unitId) {
        Float decimalAverageScore = reviewsTestRepository.calculateUnitAverageScore(UUID.fromString(unitId)).orElseThrow();

        return new ResponseEntity<>(Math.round(decimalAverageScore), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Review>> getReviewsByUnitId(String unitId) {
        return new ResponseEntity<>(reviewsTestRepository.getAllByUnitId(UUID.fromString(unitId)), HttpStatus.OK);
    }
}
