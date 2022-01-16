package com.rentaplace.functionaltests.requests.users

import com.rentaplace.functionaltests.domain.User
import com.rentaplace.functionaltests.dto.UserCreationRequestDto
import com.rentaplace.functionaltests.exception.HttpErrorResponse
import com.rentaplace.functionaltests.requests.Request

import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class CreateNewUserRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/test/v1/users'
    private UserCreationRequestDto dto

    CreateNewUserRequest(RestTemplate restTemplate, UserCreationRequestDto dto) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.dto = dto
    }

    @Override
    def execute() {
        try {
            restTemplate.postForObject(url, dto, User)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
