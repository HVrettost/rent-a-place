package com.blueground.functionaltests.requests.units

import com.blueground.functionaltests.domain.Unit
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitsBySearchValueRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/units?search=%s&page=%d&pageSize=%d'

    GetUnitsBySearchValueRequest(RestTemplate restTemplate, String searchValue, Integer page, Integer pageSize) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, searchValue, page, pageSize)
    }

    @Override
    def execute() {
        try {
            restTemplate.getForEntity(url, Unit[])
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}