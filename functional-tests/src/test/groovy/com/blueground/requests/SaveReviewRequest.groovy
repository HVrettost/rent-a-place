package com.blueground.requests

import com.blueground.domain.ReviewDto
import com.blueground.exception.HttpErrorResponse
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class SaveReviewRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/reviews'
    private ReviewDto reviewDto

    SaveReviewRequest(RestTemplate restTemplate, ReviewDto reviewDto) {
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
