package com.blueground.units.dao

import com.blueground.units.model.domain.PageReq
import com.blueground.units.model.dto.UnitDto
import com.blueground.units.model.entity.Unit
import com.blueground.units.repository.UnitsRepository
import org.springframework.core.convert.converter.Converter
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import spock.lang.Specification

class DefaultUnitsDaoTest extends Specification {

    private UnitsRepository unitsRepository
    private Converter<Unit, UnitDto> converter
    private RetrieveUnitsDao unitDao

    def setup() {
        unitsRepository = Mock()
        converter = Mock()
        unitDao = new DefaultRetrieveUnitsDao(unitsRepository, converter)
    }

    def "should return empty list if retrieved number of units is 0"() {
        given:
            def searchValue = 'search value'
            def pageRequest = new PageReq(0, 2)
            def slice = new SliceImpl([])

        when:
            def response = unitDao.getUnitsBySearchValueFromTokens(searchValue, pageRequest)

        then:
            1 * unitsRepository.findUnitsByVectorizedValues(searchValue, _ as Pageable) >> slice
            0 * _

        and:
            response.size() == 0
    }

    def "should return non empty list if retrieved number of units is non 0"() {
        given:
            def searchValue = 'search value'
            def pageRequest = new PageReq(0, 2)
            def slice = new SliceImpl([Mock(Unit), Mock(Unit)])

        when:
            def response = unitDao.getUnitsBySearchValueFromTokens(searchValue, pageRequest)

        then:
            1 * unitsRepository.findUnitsByVectorizedValues(searchValue, _ as Pageable) >> slice
            2 * converter.convert(_ as Unit)
            0 * _

        and:
            response.size() == 2
    }

}
