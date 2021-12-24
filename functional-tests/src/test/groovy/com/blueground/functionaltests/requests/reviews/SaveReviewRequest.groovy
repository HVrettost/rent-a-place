package com.blueground.functionaltests.requests.reviews


import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.requests.Request
import com.blueground.functionaltests.domain.Review
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
