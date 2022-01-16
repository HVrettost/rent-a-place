package com.rentaplace.functionaltests.actions

import com.rentaplace.functionaltests.dto.UnitCreationRequestDto
import com.rentaplace.functionaltests.dto.UserCreationRequestDto
import com.rentaplace.functionaltests.requests.Request
import com.rentaplace.functionaltests.requests.reviews.GetReviewsByUnitIdRequest
import com.rentaplace.functionaltests.requests.units.CreateNewUnitRequest
import com.rentaplace.functionaltests.requests.units.DeleteAllUnitsRequest
import com.rentaplace.functionaltests.requests.units.GetUnitAverageScoreRequest
import com.rentaplace.functionaltests.requests.users.CreateNewUserRequest

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
}
