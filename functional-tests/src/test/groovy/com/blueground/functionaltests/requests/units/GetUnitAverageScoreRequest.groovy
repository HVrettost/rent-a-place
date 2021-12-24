package com.blueground.functionaltests.requests.units

import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitAverageScoreRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/test/units/score?unitId=%s'

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
