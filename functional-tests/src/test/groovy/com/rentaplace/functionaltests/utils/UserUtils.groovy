package com.rentaplace.functionaltests.utils

import com.rentaplace.functionaltests.dto.UserAuthType
import com.rentaplace.functionaltests.dto.UserCreationRequestDto

trait UserUtils {

    UserCreationRequestDto createSimpleSubscribedUserRequest(String username, String password, String name = 'name', String surname = 'surname') {
        new UserCreationRequestDto(username: username, password: password, name: name, surname: surname, userAuthType: UserAuthType.SUBSCRIBEDSIMPLE.authRoleValue)
    }

    UserCreationRequestDto createPremiumSubscribedUserRequest(String username, String password, String name = 'name', String surname = 'surname') {
        new UserCreationRequestDto(username: username, password: password, name: name, surname: surname, userAuthType: UserAuthType.SUBSCRIBEDPREMIUM.authRoleValue)
    }

    UserCreationRequestDto createAdminUserRequest(String username, String password, String name = 'name', String surname = 'surname') {
        new UserCreationRequestDto(username: username, password: password, name: name, surname: surname, userAuthType: UserAuthType.ADMIN.authRoleValue)
    }
}
