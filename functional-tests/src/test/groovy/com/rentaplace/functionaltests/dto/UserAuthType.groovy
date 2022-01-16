package com.rentaplace.functionaltests.dto

enum UserAuthType {

    ADMIN("ADMIN_USER"),
    SUBSCRIBEDSIMPLE("SUBSCRIBED_SIMPLE_USER"),
    SUBSCRIBEDPREMIUM("SUBSCRIBED_PREMIUM_USER")

    private final String authRoleValue

    UserAuthType(String authRoleValue) {
        this.authRoleValue = authRoleValue
    }

    String getAuthRoleValue() {
        this.authRoleValue
    }

}