package com.blueground.requests.reviews

import com.blueground.domain.Review
import com.blueground.exception.HttpErrorResponse
import com.blueground.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetReviewsByUnitIdRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/test/reviews?unitId=%s'

    GetReviewsByUnitIdRequest(RestTemplate restTemplate, UUID unitId) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, unitId)
    }

    @Override
    def execute() {
        try {
            restTemplate.getForObject(url, Review[])
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
