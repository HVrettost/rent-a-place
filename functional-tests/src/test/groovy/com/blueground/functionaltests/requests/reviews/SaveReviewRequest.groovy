package com.blueground.functionaltests.requests.reviews

import com.blueground.functionaltests.domain.Review
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import org.springframework.http.HttpEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class SaveReviewRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/v1/reviews'
    private Review reviewDto
    def cookie
    private String userAgent

    SaveReviewRequest(RestTemplate restTemplate, Review reviewDto, def cookie, String userAgent) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.reviewDto = reviewDto
        this.cookie = cookie
        this.userAgent = userAgent
    }

    @Override
    def execute() {
        try {
            HttpEntity<Review> request = new HttpEntity<>(reviewDto, createHeadersWithCookie(cookie, userAgent))
            restTemplate.postForEntity(url, request, Void)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
