package com.blueground.functionaltests.requests.auth

import com.blueground.functionaltests.dto.AuthenticationRequestDto
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class AuthenticateAndGetAccessTokenRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/auth/token/access'
    private AuthenticationRequestDto dto

    AuthenticateAndGetAccessTokenRequest(RestTemplate restTemplate, AuthenticationRequestDto dto) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.dto = dto
    }

    @Override
    def execute() {
        try {
            restTemplate.postForEntity(url, dto, Void)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
