package com.rentaplace.functionaltests.requests.units

import com.rentaplace.functionaltests.exception.HttpErrorResponse
import com.rentaplace.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitAverageScoreRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/test/v1/units/score?unitId=%s'

    GetUnitAverageScoreRequest(RestTemplate restTemplate, UUID unitId) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, unitId)
    }

    @Override
    def execute() {
        try {
            restTemplate.getForObject(url, Integer)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
