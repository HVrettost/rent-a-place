package com.rentaplace.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthType {

    ADMIN("ADMIN_USER"),
    SUBSCRIBEDSIMPLE("SUBSCRIBED_SIMPLE_USER"),
    SUBSCRIBEDPREMIUM("SUBSCRIBED_PREMIUM_USER");

    private final String authRoleValue;
}
