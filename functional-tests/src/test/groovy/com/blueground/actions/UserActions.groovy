package com.blueground.actions

import com.blueground.domain.ReviewDto
import com.blueground.requests.Request
import com.blueground.requests.SaveReviewRequest
import org.springframework.web.client.RestTemplate

trait UserActions {

    def saveReview(RestTemplate restTemplate, ReviewDto reviewDto) {
        Request request = new SaveReviewRequest(restTemplate, reviewDto)
        request.execute()
    }
}
