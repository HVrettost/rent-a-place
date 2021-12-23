package com.blueground.test.controller;

import com.blueground.reviews.model.entity.Review;
import com.blueground.test.api.ReviewsTestApi;
import com.blueground.test.repository.ReviewsTestRepository;
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
