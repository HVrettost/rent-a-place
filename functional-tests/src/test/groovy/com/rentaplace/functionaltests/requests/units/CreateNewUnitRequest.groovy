package com.rentaplace.functionaltests.requests.units

import com.rentaplace.functionaltests.domain.Unit
import com.rentaplace.functionaltests.dto.UnitCreationRequestDto
import com.rentaplace.functionaltests.exception.HttpErrorResponse
import com.rentaplace.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class CreateNewUnitRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/rentaplace/test/v1/units'
    private UnitCreationRequestDto dto

    CreateNewUnitRequest(RestTemplate restTemplate, UnitCreationRequestDto dto) {
        this.restTemplate = restTemplate
        url = String.format(url, HOST)
        this.dto = dto
    }

    @Override
    def execute() {
        try {
            restTemplate.postForObject(url, dto, Unit)
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            new HttpErrorResponse(exception.rawStatusCode, exception.responseBodyAsString)
        }
    }
}
