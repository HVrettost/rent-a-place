package com.blueground.test.api;

import com.blueground.reviews.model.entity.Review;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "marsrental/v1/test")
public interface ReviewsTestApi {

    @DeleteMapping(value = "reviews/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAllReviews();

    @GetMapping(value = "reviews/score/average",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getScoreAverageForUnit(@RequestParam(name = "unitId") String unitId);

    @GetMapping(value = "reviews",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Review>> getReviewsByUnitId(@RequestParam(name = "unitId") String unitId);
}
