package com.blueground.functionaltests.dto

class UserCreationRequestDto {

    private String username
    private String password
    private String name
    private String surname

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
}
