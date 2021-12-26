package com.blueground.functionaltests.core.auth

import com.blueground.functionaltests.config.MarsRentalFTSetup
import com.blueground.functionaltests.domain.User
import com.blueground.functionaltests.dto.AuthenticationRequestDto
import com.blueground.functionaltests.dto.UserCreationRequestDto
import com.blueground.functionaltests.utils.JwtUtils
import com.blueground.functionaltests.utils.UserUtils

class AuthSpec extends MarsRentalFTSetup implements UserUtils, JwtUtils {

    def cleanup() {
        systemActor.deleteAllUsersFromDatabase(restTemplate)
    }

    def "user should authenticate successfully and receive access token"() {
        given: 'a user'
            //decrypted value translates to password
            UserCreationRequestDto request = createUserRequest('jonsnow','$2a$12$meMe5iToZsEAN0WZCHtPdO2LwQKqa..jEHt5ZsQzwrsomi3.BOFDu')
            User user = systemActor.createNewUser(restTemplate, request)

        when: 'user tries to authenticate'
            AuthenticationRequestDto dto = new AuthenticationRequestDto(username: user.username, password: 'password')
            def cookieHeader = userActor.authenticateAndGetAccessToken(restTemplate, dto).headers['Set-Cookie']

        then: 'user receives access and refresh token in the cookie header'
            with(cookieHeader) {
                size() == 2
                it[0].contains('accessToken=')
                it[1].contains('refreshToken=')
            }

        and: 'access and refresh token are valid'
            verifyToken(cookieHeader[0].split(';')[0].replace('accessToken=', ''))
            verifyToken(cookieHeader[1].split(';')[0].replace('refreshToken=', ''))
    }

}
