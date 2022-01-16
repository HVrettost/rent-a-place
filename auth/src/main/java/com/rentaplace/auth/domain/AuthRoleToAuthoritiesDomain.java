package com.rentaplace.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleToAuthoritiesDomain {

    private UUID id;
    private String role;
    private String authorities;
}
