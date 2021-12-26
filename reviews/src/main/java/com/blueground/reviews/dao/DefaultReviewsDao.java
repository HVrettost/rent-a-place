package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.reviews.model.entity.Review;
import com.blueground.reviews.repository.ReviewsRepository;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.repository.UnitsRepository;
import lombok.NonNull;
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
            checkIfReviewAlreadyExists(entity.getUnitId(), entity.getUserId());
            reviewsRepository.save(entity);
        } catch (Exception ex) {
            if (ex instanceof ReviewsException) {
                throw ex;
            }

            throw new ReviewsException(ReviewsErrorCodes.ERROR_REVIEW_SAVE);
        }
    }

    @Override
    public Integer calculateReviewsAverageScoreForUnit(UUID unitId) throws ReviewsException {
        Float decimalAverageScore = reviewsRepository.calculateUnitAverageScore(unitId)
                .orElseThrow(() -> new ReviewsException(ReviewsErrorCodes.AVERAGE_SCORE_CALCULATION_INABILITY));

        return Math.round(decimalAverageScore);
    }

    @Override
    public void updateAverageScoreForUnit(Integer averageScore, UUID unitId) throws UnitsException{
        try {
            unitsRepository.updateUnitAverageScore(averageScore, unitId);
        } catch (Exception ex) {
            throw new UnitsException(UnitsErrorCodes.COULD_NOT_UPDATE_SCORE);
        }
    }

    private void checkIfReviewAlreadyExists(UUID unitId, UUID userId) throws ReviewsException {
        if (reviewsRepository.existsByUnitIdAndUserId(unitId, userId)) {
            throw new ReviewsException(ReviewsErrorCodes.REVIEW_ALREADY_EXISTS);
        }
    }
}
