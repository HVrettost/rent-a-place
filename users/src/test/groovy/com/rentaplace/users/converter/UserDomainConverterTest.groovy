package com.rentaplace.users.converter

import com.rentaplace.users.model.domain.UserDomain
import com.rentaplace.users.model.entity.User
import org.modelmapper.ModelMapper
import spock.lang.Specification

class UserDomainConverterTest extends Specification {

    private ModelMapper modelMapper
    private UserDomainConverter userDomainConverter

    def setup() {
        modelMapper = Mock()
        userDomainConverter = new UserDomainConverter(modelMapper)
    }

    def "should map user entity to user domain object successfully"() {
        given:
            def user = Mock(User)
            def userDomain = Mock(UserDomain)

        when:
            def response = userDomainConverter.convert(user)

        then:
            1 * modelMapper.map(user, UserDomain) >> userDomain
            0 * _

        and:
            response == userDomain
    }
}
