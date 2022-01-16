package com.rentaplace.reviews.dao;

import com.rentaplace.reviews.exception.ReviewsException;
import com.rentaplace.reviews.model.dto.ReviewDto;
import com.rentaplace.reviews.exception.error.ReviewsErrorCodes;
import com.rentaplace.reviews.model.entity.Review;
import com.rentaplace.reviews.repository.ReviewsRepository;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.repository.RetrieveUnitsRepository;
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
    private final RetrieveUnitsRepository unitsRepository;
    private final Converter<ReviewDto, Review> converter;

    @Override
    public void insertReviewInDatabase(ReviewDto review) {
        Review entity = converter.convert(review);
        //No need to add catch exception here as I am not flushing immediately the entity
        //in the database so the catch clause would not catch the exception here
        //The transaction will be caught in the handler in the aspect(ReviewsExceptionHandler).
        //If I flush here and the subsequent db calls for updating the score fails there will be inconsistency as the average score
        //will not depict the actual score of the unit
        //@Transaction annotation in service layer will rollback the whole transaction and consistency will be achieved
        //@Transactional rolls back for all runtime exceptions as is, so exception thrown by converter and repository will be caught
        reviewsRepository.save(entity);
    }

    @Override
    public Integer calculateReviewsAverageScoreForUnit(UUID unitId) throws ReviewsException {
        Float averageScore = reviewsRepository.calculateUnitAverageScore(unitId)
                .orElseThrow(() -> new ReviewsException(ReviewsErrorCodes.AVERAGE_SCORE_CALCULATION_INABILITY));

        return Math.round(averageScore);
    }

    @Override
    public void updateAverageScoreForUnit(Integer averageScore, UUID unitId) throws UnitsException {
        //Same as above, no need to add catch block here.
        //Exception will be delegated through callstack in the advice handler
        unitsRepository.updateUnitAverageScore(averageScore, unitId);
    }
}
