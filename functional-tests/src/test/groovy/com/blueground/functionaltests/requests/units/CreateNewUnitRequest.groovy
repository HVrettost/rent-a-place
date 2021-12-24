package com.blueground.functionaltests.requests.units


import com.blueground.functionaltests.dto.UnitCreationRequestDto
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.domain.Unit
import com.blueground.functionaltests.requests.Request
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

class CreateNewUnitRequest implements Request {

    private RestTemplate restTemplate
    private String url = '%s/marsrental/v1/test/units'
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
