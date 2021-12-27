package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.reviews.model.entity.Review;
import com.blueground.reviews.repository.ReviewsRepository;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.repository.UnitsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DefaultReviewsDao implements ReviewsDao {

    private final ReviewsRepository reviewsRepository;
    private final UnitsRepository unitsRepository;
    private final Converter<ReviewDto, Review> reviewDtoConverter;

    @Override
    public void insertReviewInDatabase(ReviewDto review) throws ReviewsException {
        Review entity = reviewDtoConverter.convert(review);
        try {
            reviewsRepository.save(entity);
        } catch (Exception ex) {
            throw new ReviewsException(ReviewsErrorCodes.ERROR_REVIEW_SAVE, ex);
        }
    }

    @Override
    public Integer calculateReviewsAverageScoreForUnit(UUID unitId) throws ReviewsException {
        Float averageScore = reviewsRepository.calculateUnitAverageScore(unitId)
                .orElseThrow(() -> new ReviewsException(ReviewsErrorCodes.AVERAGE_SCORE_CALCULATION_INABILITY));

        return Math.round(averageScore);
    }

    @Override
    public void updateAverageScoreForUnit(Integer averageScore, UUID unitId) throws UnitsException {
        try {
            unitsRepository.updateUnitAverageScore(averageScore, unitId);
        } catch (Exception ex) {
            throw new UnitsException(UnitsErrorCodes.COULD_NOT_UPDATE_SCORE);
        }
    }
}
