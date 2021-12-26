package com.blueground.functionaltests.utils

import com.blueground.functionaltests.dto.UserCreationRequestDto

trait UserUtils {

    UserCreationRequestDto createUserRequest(String username, String password, String name = 'name', String surname = 'surname') {
        new UserCreationRequestDto(username: username, password: password, name: name, surname: surname)
    }

}
