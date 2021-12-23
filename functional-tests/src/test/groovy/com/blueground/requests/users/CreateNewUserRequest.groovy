package com.blueground.requests.users

import com.blueground.domain.User
import com.blueground.exception.HttpErrorResponse
import com.blueground.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class CreateNewUserRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/test/users'
    private UUID userId

    CreateNewUserRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.userId = userId
    }

    @Override
    def execute() {
        try {
            restTemplate.postForObject(url, Void, User)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
