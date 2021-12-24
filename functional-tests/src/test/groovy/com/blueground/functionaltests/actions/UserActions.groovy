package com.blueground.functionaltests.actions


import com.blueground.functionaltests.requests.Request
import com.blueground.functionaltests.requests.reviews.SaveReviewRequest
import com.blueground.functionaltests.requests.units.GetUnitsBySearchValueRequest
import com.blueground.functionaltests.domain.Review
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
