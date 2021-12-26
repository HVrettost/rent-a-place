package com.blueground.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthType {

    SIMPLE("SIMPLE_USER"), PREMIUM("PREMIUM_USER");

    private final String authRoleValue;
}
