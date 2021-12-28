package com.blueground.units.service

import com.blueground.units.dao.UnitsDao
import com.blueground.units.model.domain.PageReq
import com.blueground.units.model.dto.UnitDto
import spock.lang.Specification

class DefaultUnitsDaoTest extends Specification {

    private UnitsDao unitsDao
    private UnitsService unitsService

    def setup() {
        unitsDao = Mock()
        unitsService = new DefaultUnitsService(unitsDao)
    }

    def "should propagate call to dao and return result list of unit dtos"() {
        given:
            def searchValue = 'search value'
            def pageRequest = new PageReq(0, 2)
            def unitDtoList = [Mock(UnitDto), Mock(UnitDto)]

        when:
            def response = unitsService.getUnitsByTitleAnRegion(searchValue, pageRequest)

        then:
            1 * unitsDao.getUnitsBySearchValueFromTokens(searchValue, pageRequest) >> unitDtoList
            0 * _
        and:
            response.size() == 2
    }

}
