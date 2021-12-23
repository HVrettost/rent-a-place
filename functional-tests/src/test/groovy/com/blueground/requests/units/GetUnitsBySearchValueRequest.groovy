package com.blueground.requests.units

import com.blueground.domain.Unit
import com.blueground.exception.HttpErrorResponse
import com.blueground.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitsBySearchValueRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/units?search=%s&page=%d&pageSize=%d'

    GetUnitsBySearchValueRequest(RestTemplate restTemplate, String searchValue, int page, int pageSize) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, searchValue, page, pageSize)
    }

    @Override
    def execute() {
        try {
            restTemplate.getForObject(url, Unit[])
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
