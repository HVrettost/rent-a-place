package com.blueground.functionaltests.actions

import com.blueground.functionaltests.dto.UnitCreationRequestDto
import com.blueground.functionaltests.dto.UserCreationRequestDto
import com.blueground.functionaltests.requests.Request
import com.blueground.functionaltests.requests.reviews.GetReviewsByUnitIdRequest
import com.blueground.functionaltests.requests.units.CreateNewUnitRequest
import com.blueground.functionaltests.requests.units.DeleteAllUnitsRequest
import com.blueground.functionaltests.requests.units.GetUnitAverageScoreRequest
import com.blueground.functionaltests.requests.users.CreateNewUserRequest
import com.blueground.functionaltests.requests.users.DeleteAllUsersRequest
import org.springframework.web.client.RestTemplate

trait SystemActions {

    def getAverageScore(RestTemplate restTemplate, UUID unitId) {
        Request request = new GetUnitAverageScoreRequest(restTemplate, unitId)
        request.execute()
    }

    def createNewUser(RestTemplate restTemplate, UserCreationRequestDto dto) {
        Request request = new CreateNewUserRequest(restTemplate, dto)
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

    def deleteAllUsersFromDatabase(RestTemplate restTemplate) {
        Request request = new DeleteAllUsersRequest(restTemplate)
        request.execute()
    }
}
