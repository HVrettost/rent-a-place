package com.blueground.core.units

import com.blueground.config.MarsRentalFTSetup
import com.blueground.domain.Unit
import com.blueground.domain.User
import com.blueground.dto.UnitCreationRequestDto

class UnitsSpec extends MarsRentalFTSetup {

    def "should return error if searchValue passed is null or empty"() {

    }

    def "should return all units paged according to search value successfully"() {
        given: '6 units'
            Unit unit1 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region', 'title', new BigDecimal("500")))
            Unit unit2 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region2', 'title2', new BigDecimal("800")))
            Unit unit3 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region3', 'title3', new BigDecimal("345")))
            Unit unit4 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('random sentence', 'random stuff', new BigDecimal("980")))
            Unit unit5 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('hey hey', 'ho ho' , new BigDecimal("1000")))
            Unit unit6 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('region4', 'ho ho la la', new BigDecimal("290")))

        and: 'a user'
            User user = systemActor.createNewUser(restTemplate)

        when: 'a call is made by the user to get the first page for the available units according to a search value'
            def searchValue = 'region'
            def units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 0, 2)

        then:
            units.size() == 1
    }
}
