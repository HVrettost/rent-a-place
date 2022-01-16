package com.rentaplace.units.service

import com.rentaplace.units.dao.RetrieveUnitsDao
import com.rentaplace.units.model.domain.PageReq
import com.rentaplace.units.model.dto.UnitDto
import spock.lang.Specification

class DefaultRetrieveUnitsServiceTest extends Specification {

    private RetrieveUnitsDao unitsDao
    private RetrieveUnitsService unitsService

    def setup() {
        unitsDao = Mock()
        unitsService = new DefaultRetrieveUnitsService(unitsDao)
    }

    def "should propagate call to dao and return result list of unit dtos"() {
        given:
            def searchValue = 'search value'
            def pageRequest = new PageReq(0, 2)
            def unitDtoList = [GroovyMock(UnitDto), GroovyMock(UnitDto)]

        when:
            def response = unitsService.getUnitsByTitleAnRegion(searchValue, pageRequest)

        then:
            1 * unitsDao.getUnitsBySearchValueFromTokens(searchValue, pageRequest) >> unitDtoList
            0 * _
        and:
            response.size() == 2
    }

}
