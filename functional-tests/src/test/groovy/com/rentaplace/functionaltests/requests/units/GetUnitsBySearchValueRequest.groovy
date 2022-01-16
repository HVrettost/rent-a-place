package com.rentaplace.functionaltests.requests.units

import com.rentaplace.functionaltests.domain.Unit
import com.rentaplace.functionaltests.exception.HttpErrorResponse
import com.rentaplace.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitsBySearchValueRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/v1/units?search=%s&page=%d&pageSize=%d'
    private def cookie
    private String userAgent

    GetUnitsBySearchValueRequest(RestTemplate restTemplate, String searchValue, Integer page, Integer pageSize) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, searchValue, page, pageSize)
        this.cookie = cookie
        this.userAgent = userAgent
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
