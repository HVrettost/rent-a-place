package com.rentaplace.functionaltests.actions

import com.rentaplace.functionaltests.domain.Review
import com.rentaplace.functionaltests.dto.AuthenticationRequestDto
import com.rentaplace.functionaltests.requests.Request
import com.rentaplace.functionaltests.requests.auth.AuthenticateAndGetAccessTokenRequest
import com.rentaplace.functionaltests.requests.reviews.SaveReviewRequest
import com.rentaplace.functionaltests.requests.units.GetUnitsBySearchValueRequest
import org.springframework.web.client.RestTemplate

trait UserActions {

    def saveReview(RestTemplate restTemplate, Review reviewDto, def cookie, String userAgent) {
        Request request = new SaveReviewRequest(restTemplate, reviewDto, cookie, userAgent)
        request.execute()
    }

    def getUnitsPagedBySearchValue(RestTemplate restTemplate, String searchValue, Integer page, Integer pageSize) {
        Request request = new GetUnitsBySearchValueRequest(restTemplate, searchValue, page, pageSize)
        request.execute()
    }

    def authenticateAndGetAccessToken(RestTemplate restTemplate, AuthenticationRequestDto authenticationRequestDto, String userAgent) {
        Request request = new AuthenticateAndGetAccessTokenRequest(restTemplate, authenticationRequestDto, userAgent)
        request.execute()
    }
}
