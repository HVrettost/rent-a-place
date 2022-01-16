package com.rentaplace.users.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDomain {

    private UUID userId;
    private String name;
    private String surname;
    private String username;
    private String password;
}
