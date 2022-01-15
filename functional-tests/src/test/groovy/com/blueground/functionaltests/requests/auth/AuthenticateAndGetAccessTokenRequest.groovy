package com.blueground.functionaltests.requests.auth

import com.blueground.functionaltests.dto.AuthenticationRequestDto
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.http.HttpEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class AuthenticateAndGetAccessTokenRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/v1/auth/token/access'
    private AuthenticationRequestDto dto
    private String userAgent

    AuthenticateAndGetAccessTokenRequest(RestTemplate restTemplate, AuthenticationRequestDto dto, String userAgent) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.dto = dto
        this.userAgent = userAgent
    }

    @Override
    def execute() {
        try {
            HttpEntity<AuthenticationRequestDto> request = new HttpEntity<>(dto, createHeadersWithoutCookie(userAgent))
            restTemplate.postForEntity(url, request, Void)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
