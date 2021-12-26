package com.blueground.functionaltests.core.units

import com.blueground.functionaltests.config.MarsRentalFTSetup
import com.blueground.functionaltests.domain.Review
import com.blueground.functionaltests.domain.Unit
import com.blueground.functionaltests.domain.User
import com.blueground.functionaltests.dto.UnitCreationRequestDto
import com.blueground.functionaltests.dto.UserCreationRequestDto
import com.blueground.functionaltests.exception.ErrorDetails
import com.blueground.functionaltests.exception.HttpErrorResponse
import com.blueground.functionaltests.exception.UnitsErrorCodes
import com.blueground.functionaltests.utils.UserUtils
import spock.lang.Unroll

class UnitsSpec extends MarsRentalFTSetup implements UserUtils {

    def cleanup() {
        systemActor.deleteAllUnitsFromDatabase(restTemplate)
        systemActor.deleteAllUsersFromDatabase(restTemplate)
    }

    def "should return error if searchValue passed is null or empty"() {
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and:
            def searchValue = ''

        when:
            HttpErrorResponse response = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 0, 2, cookieWithAccessToken, userAgent)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == UnitsErrorCodes.EMPTY_SEARCH_VALUE.applicationErrorCode
                description == UnitsErrorCodes.EMPTY_SEARCH_VALUE.description
            }
    }

    @Unroll
    def "should return error if page request values are invalid"() {
        given: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and:
            def searchValue = 'a search value'

        when:
            HttpErrorResponse response = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, page, pageSize, cookieWithAccessToken, userAgent)
            def deserializedResponse = objectMapper.readValue(response.errorResponseAsString, ErrorDetails)

        then:
            response.rawHttpStatus == 400
            with(deserializedResponse) {
                applicationErrorCode == UnitsErrorCodes.INVALID_PAGE_REQUEST.applicationErrorCode
                description == UnitsErrorCodes.INVALID_PAGE_REQUEST.description
            }

        where:
            page | pageSize
            -1   | 2
            -200 | 7
            -47  | 0
            0    | 0
    }

    def "should return all units paged according to search value successfully ordered by score descending"() {
        given: '7 units'
            Unit unit1 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Majestic Overview in Sea', new BigDecimal("500")))
            Unit unit2 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'City Pleasure', new BigDecimal("800")))
            Unit unit3 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Dubai', 'Dessert magic', new BigDecimal("345")))
            Unit unit4 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('New York', 'Trade Center View', new BigDecimal("980")))
            Unit unit5 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Acropolis View' , new BigDecimal("1000")))
            Unit unit6 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Lycabettus Majesty', new BigDecimal("290")))
            Unit unit7 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Kolonaki Avenue', new BigDecimal("1299")))

        and: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and: 'reviews are done in some of the units from the user'
            userActor.saveReview(restTemplate, new Review(score: 4, unitId: unit1.unitId, comment: 'comment 1', userId: user.userId), cookieWithAccessToken, userAgent)
            userActor.saveReview(restTemplate, new Review(score: 5, unitId: unit2.unitId, comment: 'comment 2', userId: user.userId), cookieWithAccessToken, userAgent)
            userActor.saveReview(restTemplate, new Review(score: 1, unitId: unit7.unitId, comment: 'comment 3', userId: user.userId), cookieWithAccessToken, userAgent)
            userActor.saveReview(restTemplate, new Review(score: 3, unitId: unit5.unitId, comment: 'comment 4', userId: user.userId), cookieWithAccessToken, userAgent)

        when: 'a call is made by the user to get the first page for the available units according to given the keyword Athens'
            def searchValue = 'Athens'
            def units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 0, 2, cookieWithAccessToken, userAgent)

        then: 'it should be sorted based on average score'
            with (units.body) {
                with (it[0]) {
                    title == 'City Pleasure'
                    averageScore == 5
                }
                with (it[1]) {
                    title == 'Majestic Overview in Sea'
                    averageScore == 4
                }
            }

        when: 'a call is made by the user to get the second page for the available units according to given the keyword Athens'
            units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 1, 2, cookieWithAccessToken, userAgent)

        then: 'it should be sorted based on average score'
            with (units.body) {
                with (it[0]) {
                    title == 'Acropolis View'
                    averageScore == 3
                }
                with (it[1]) {
                    title == 'Kolonaki Avenue'
                    averageScore == 1
                }
            }

        when: 'a call is made by the user to get the last page for the available units according to given the keyword Athens'
            units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 2, 2, cookieWithAccessToken, userAgent)

        then: 'it should be sorted based on average score'
            with (units.body) {
                with (it[0]) {
                    title == 'Lycabettus Majesty'
                    averageScore == null
                }
            }
    }


    def "should return all units paged according to search value successfully with given as keyword part of the title"() {
        given: '7 units'
            Unit unit1 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Majestic Overview in Sea', new BigDecimal("500")))
            Unit unit2 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'City Pleasure', new BigDecimal("800")))
            Unit unit3 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Dubai', 'Dessert magic', new BigDecimal("345")))
            Unit unit4 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('New York', 'Trade Center Majestic View', new BigDecimal("980")))
            Unit unit5 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Acropolis View' , new BigDecimal("1000")))
            Unit unit6 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Lycabettus Majesty', new BigDecimal("290")))
            Unit unit7 = systemActor.createNewUnit(restTemplate, new UnitCreationRequestDto('Athens', 'Kolonaki Majestic Avenue', new BigDecimal("1299")))

        and: 'a user with access token'
            def username = UUID.randomUUID().toString()
            def password = 'password'
            UserCreationRequestDto request = createUserRequest(username, password)
            User user = systemActor.createNewUser(restTemplate, request)
            def userAgent = 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'
            def cookieWithAccessToken = createAccessTokenForUser(userAgent, username, password)

        and: 'reviews are done in some of the units from the user'
            userActor.saveReview(restTemplate, new Review(score: 4, unitId: unit1.unitId, comment: 'comment 1', userId: user.userId), cookieWithAccessToken, userAgent)
            userActor.saveReview(restTemplate, new Review(score: 5, unitId: unit4.unitId, comment: 'comment 2', userId: user.userId), cookieWithAccessToken, userAgent)

        when: 'a call is made by the user to get the first page for the available units according to keyword MAJESTIC(check if relaxed rules for CASE INSENSITIVE works as expected)'
            def searchValue = 'MAJESTIC'
            def units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 0, 10, cookieWithAccessToken, userAgent)

        then: 'it should fetch results sorted in just one page'
            with (units.body) {
                with (it[0]) {
                    title == 'Trade Center Majestic View'
                    averageScore == 5
                }
                with (it[1]) {
                    title == 'Majestic Overview in Sea'
                    averageScore == 4
                }

                with (it[2]) {
                    title == 'Kolonaki Majestic Avenue'
                    averageScore == null
                }
            }

        when: 'a call is made by the user to get the second page  but no results are fetched'
            units = userActor.getUnitsPagedBySearchValue(restTemplate, searchValue, 1, 10, cookieWithAccessToken, userAgent)

        then: 'it should be sorted based on average score'
            units.body.length == 0
    }
}
