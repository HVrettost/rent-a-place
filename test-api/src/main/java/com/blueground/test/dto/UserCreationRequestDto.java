package com.blueground.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequestDto {

    private String username;
    private String password;
    private String name;
    private String surname;
}
