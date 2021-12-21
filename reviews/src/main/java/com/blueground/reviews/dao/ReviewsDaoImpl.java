package com.blueground.reviews.dao;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.domain.ReviewDto;
import com.blueground.reviews.exception.error.ReviewsErrorCodes;
import com.blueground.reviews.exception.error.ReviewsErrorDetails;
import com.blueground.reviews.exception.utils.ReviewsExceptionUtils;
import com.blueground.reviews.model.Review;
import com.blueground.reviews.repository.ReviewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewsDaoImpl implements ReviewsDao {

    private final ReviewsRepository reviewsRepository;
    private final Converter<ReviewDto, Review> reviewDtoConverter;

    @Override
    @Transactional
    public void insertReviewInDatabase(ReviewDto review) throws ReviewsException {
        Review entity = Optional.ofNullable(reviewDtoConverter.convert(review))
                .orElseThrow(() -> new ReviewsException(
                        ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.GENERIC_REVIEWS_ERROR)));
        try {
            checkIfReviewAlreadyExists(entity.getUnitId(), entity.getUserId());
            reviewsRepository.save(entity);
        } catch (Exception ex) {
            if (ex instanceof ReviewsException) {
                throw ex;
            }

            throw new ReviewsException(ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.ERROR_REVIEW_SAVE));
        }
    }

    private void checkIfReviewAlreadyExists(UUID unitId, UUID userId) throws ReviewsException {
        if (reviewsRepository.existsByUnitIdAndUserId(unitId, userId)) {
            throw new ReviewsException(ReviewsExceptionUtils.createErrorDetails(ReviewsErrorCodes.REVIEW_ALREADY_EXISTS));
        }
    }
}
