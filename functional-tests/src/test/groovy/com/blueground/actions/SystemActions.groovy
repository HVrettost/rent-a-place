package com.blueground.actions

import com.blueground.dto.UnitCreationRequestDto
import com.blueground.requests.units.CreateNewUnitRequest
import com.blueground.requests.units.DeleteAllUnitsRequest
import com.blueground.requests.users.CreateNewUserRequest
import com.blueground.requests.reviews.GetReviewsByUnitIdRequest
import com.blueground.requests.units.GetUnitAverageScoreRequest
import com.blueground.requests.Request
import org.springframework.web.client.RestTemplate

trait SystemActions {

    def getAverageScore(RestTemplate restTemplate, UUID unitId) {
        Request request = new GetUnitAverageScoreRequest(restTemplate, unitId)
        request.execute()
    }

    def createNewUser(RestTemplate restTemplate) {
        Request request = new CreateNewUserRequest(restTemplate)
        request.execute()
    }

    def createNewUnit(RestTemplate restTemplate, UnitCreationRequestDto unitCreationRequestDto) {
        Request request = new CreateNewUnitRequest(restTemplate, unitCreationRequestDto)
        request.execute()
    }

    def getReviewsByUnitId(RestTemplate restTemplate, UUID unitId) {
        Request request = new GetReviewsByUnitIdRequest(restTemplate, unitId)
        request.execute()
    }

    def deleteAllUnitsFromDatabase(RestTemplate restTemplate) {
        Request request = new DeleteAllUnitsRequest(restTemplate)
        request.execute()
    }
}
