package com.blueground.users.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID userId;
    private String name;
    private String surname;
    private String username;
    private String password;
}
