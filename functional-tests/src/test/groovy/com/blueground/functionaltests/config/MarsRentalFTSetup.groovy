package com.blueground.functionaltests.config


import com.blueground.functionaltests.actors.SystemActor
import com.blueground.functionaltests.actors.UserActor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

class MarsRentalFTSetup extends Specification {

    @Shared
    UserActor userActor

    @Shared
    SystemActor systemActor

    @Shared
    RestTemplate restTemplate

    @Shared
    ObjectMapper objectMapper

    def setupSpec() {
        userActor = new UserActor()
        systemActor = new SystemActor()
        restTemplate = new RestTemplate()
        objectMapper = new ObjectMapper()
    }
}
