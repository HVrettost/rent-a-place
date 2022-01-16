package com.rentaplace.functionaltests.dto

class UserCreationRequestDto {

    private String username
    private String password
    private String name
    private String surname
    private String userAuthType;

    String getUsername() {
        this.username
    }

    String getPassword() {
        this.password
    }

    String getName() {
        this.name
    }

    String getSurname() {
        this.surname
    }

    String getUserAuthType() {
        this.userAuthType
    }

    void setUsername(String username) {
        this.username = username
    }

    void setPassword(String password) {
        this.password = password
    }

    void setName(String name) {
        this.name = name
    }

    void setSurname(String surname) {
        this.surname = surname
    }

    void setUserAuthType(String userAuthType) {
        this.userAuthType = userAuthType
    }
}
