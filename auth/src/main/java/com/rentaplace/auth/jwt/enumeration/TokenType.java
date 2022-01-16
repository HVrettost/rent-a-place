package com.rentaplace.auth.jwt.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {

    ACCESS("accessToken"),
    REFRESH("refreshToken");

    private final String value;
}
