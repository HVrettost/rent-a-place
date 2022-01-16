package com.rentaplace.auth.jwt.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomJwtClaims {

    AUTHORITIES("auths"),
    SUBJECT("sub");

    private final String value;
}
