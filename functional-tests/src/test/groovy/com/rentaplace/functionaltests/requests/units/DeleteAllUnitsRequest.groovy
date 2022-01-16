package com.rentaplace.functionaltests.requests.units

import com.rentaplace.functionaltests.exception.HttpErrorResponse
import com.rentaplace.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class DeleteAllUnitsRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/test/v1/units/all'

    DeleteAllUnitsRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
    }

    @Override
    def execute() {
        try {
            restTemplate.delete(url)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
