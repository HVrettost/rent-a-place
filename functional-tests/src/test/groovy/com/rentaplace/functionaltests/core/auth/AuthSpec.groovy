package com.rentaplace.functionaltests.core.auth

import com.rentaplace.functionaltests.config.RentAPlacelFTSetup
import com.rentaplace.functionaltests.domain.User
import com.rentaplace.functionaltests.dto.AuthenticationRequestDto
import com.rentaplace.functionaltests.dto.UserCreationRequestDto
import com.rentaplace.functionaltests.utils.JwtUtils
import com.rentaplace.functionaltests.utils.UserUtils
import spock.lang.Unroll

class AuthSpec extends RentAPlacelFTSetup implements UserUtils, JwtUtils {

    def "user should authenticate successfully and receive access token"() {
        given: 'a username and password'
            def username = UUID.randomUUID().toString()
            def password = 'password'

        when: 'a user tries to authenticate and get an access token for subsequent calls'
            UserCreationRequestDto request = createSimpleSubscribedUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        then: 'user receives access and refresh token in the cookie header'
            with(cookieWithAccessToken) {
                size() == 2
                it[0].contains('accessToken=')
                it[1].contains('refreshToken=')
            }

        and: 'access and refresh token are valid'
            verifyToken(cookieWithAccessToken[0].split(';')[0].replace('accessToken=', ''))
            verifyToken(cookieWithAccessToken[1].split(';')[0].replace('refreshToken=', ''))
    }

    @Unroll
    def "user tries to authenticate but passes wrong credentials and error is thrown"() {
        given: 'a user with certain username and password'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            UserCreationRequestDto request = createAdminUserRequest(username, password)
            systemActor.createNewUser(restTemplate, request)

        when: 'user tries to authenticate'
            def response = userActor.authenticateAndGetAccessToken(restTemplate, new AuthenticationRequestDto(username: user, password: pass), userAgent)

        then: 'unauthorized exception is thrown'
            response.rawHttpStatus == 401

        where:
            user          | pass
            'wrong-user'  | 'password'
            'username'    | 'wrong-pass'
    }
}
