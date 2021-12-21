package com.blueground.config

import com.blueground.actors.UserActor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

class MarsRentalFTSetup extends Specification {

    @Shared
    UserActor userActor

    @Shared
    RestTemplate restTemplate

    @Shared
    ObjectMapper objectMapper

    def setupSpec() {
        userActor = new UserActor()
        restTemplate = new RestTemplate()
        objectMapper = new ObjectMapper()
    }
}
