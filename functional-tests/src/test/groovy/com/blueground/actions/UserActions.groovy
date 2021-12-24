package com.blueground.actions

import com.blueground.domain.Review
import com.blueground.requests.Request
import com.blueground.requests.reviews.SaveReviewRequest
import com.blueground.requests.units.GetUnitsBySearchValueRequest
import org.springframework.web.client.RestTemplate

trait UserActions {

    def saveReview(RestTemplate restTemplate, Review reviewDto) {
        Request request = new SaveReviewRequest(restTemplate, reviewDto)
        request.execute()
    }

    def getUnitsPagedBySearchValue(RestTemplate restTemplate, String searchValue, Integer page, Integer pageSize) {
        Request request = new GetUnitsBySearchValueRequest(restTemplate, searchValue, page, pageSize)
        request.execute()
    }
}
