package com.blueground.functionaltests.requests.units

import com.blueground.functionaltests.domain.Unit
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class GetUnitsBySearchValueRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/v1/units?search=%s&page=%d&pageSize=%d'
    private def cookie
    private String userAgent

    GetUnitsBySearchValueRequest(RestTemplate restTemplate, String searchValue, Integer page, Integer pageSize, def cookie, String userAgent) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST, searchValue, page, pageSize)
        this.cookie = cookie
        this.userAgent = userAgent
    }

    @Override
    def execute() {
        try {
            HttpEntity<String> request = new HttpEntity<>(createHeadersWithCookie(cookie, userAgent))
            restTemplate.exchange(url, HttpMethod.GET, request, Unit[])
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
