package com.blueground.auth.jwt.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomJwtClaims {

    AUTHORITIES("auths"),
    SUBJECT("sub");

    private final String value;
}
