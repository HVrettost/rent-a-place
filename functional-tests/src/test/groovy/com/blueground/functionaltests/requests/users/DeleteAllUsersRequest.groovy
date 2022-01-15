package com.blueground.functionaltests.requests.users

import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class DeleteAllUsersRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/test/v1/users/all'

    DeleteAllUsersRequest(RestTemplate restTemplate) {
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
