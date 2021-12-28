package com.blueground.users.dao

import com.blueground.users.exception.UsersException
import com.blueground.users.exception.error.UsersErrorCodes
import com.blueground.users.model.domain.UserDomain
import com.blueground.users.model.entity.User
import com.blueground.users.repository.UsersRepository
import org.springframework.core.convert.converter.Converter
import spock.lang.Specification

class DefaultUsersDaoTest extends Specification {

    private UsersRepository usersRepository
    private Converter<User, UserDomain> converter
    private UsersDao usersDao

    def setup() {
        usersRepository = Mock()
        converter = Mock()
        usersDao = new DefaultUsersDao(usersRepository, converter)
    }

    def "should throw exception if user by username is not found"() {
        given:
            def username = "username"

        when:
            usersDao.getUserByUsername(username)

        then:
            1 * usersRepository.findByUsername(username) >> Optional.empty()
            0 * _

        and:
            UsersException exceptionThrown = thrown()
            exceptionThrown.usersErrorCodes == UsersErrorCodes.USER_DOES_NOT_EXIST
    }

    def "should get user by username and convert it to domain object successfully"() {
        given:
            def username = "username"
            def user = Mock(User)
            def userDomain = Mock(UserDomain)

        when:
            def response = usersDao.getUserByUsername(username)

        then:
            1 * usersRepository.findByUsername(username) >> Optional.of(user)
            1 * converter.convert(user) >> userDomain
            0 * _

        and:
            response == userDomain
    }
}
