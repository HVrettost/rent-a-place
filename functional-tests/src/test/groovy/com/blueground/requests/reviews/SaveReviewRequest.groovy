package com.blueground.requests.reviews

import com.blueground.domain.Review
import com.blueground.exception.HttpErrorResponse
import com.blueground.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class SaveReviewRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/reviews'
    private Review reviewDto

    SaveReviewRequest(RestTemplate restTemplate, Review reviewDto) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.reviewDto = reviewDto
    }

    @Override
    def execute() {
        try {
            restTemplate.postForEntity(url, reviewDto, Void)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
