package com.blueground.functionaltests.domain

class User {

    private UUID userId;
    private String name;
    private String surname;
    private String username;
    private String password;

    UUID getUserId() {
        this.userId;
    }

    String getName() {
        this.name;
    }

    String getSurname() {
        this.surname;
    }

    String getUsername() {
        this.username;
    }

    String getPassword() {
        this.password;
    }

    void setUserId(UUID userId) {
        this.userId = userId;
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }

}
