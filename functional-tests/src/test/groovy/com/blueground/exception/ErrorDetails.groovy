package com.blueground.exception

class ErrorDetails {

    private int errorCode
    private String description

    ErrorDetails(int errorCode, String description) {
        this.errorCode = errorCode
        this.description = description
    }

    ErrorDetails() {

    }

    int getErrorCode() {
        this.errorCode
    }

    void setErrorCode(int errorCode) {
        this.errorCode = errorCode
    }

    String getDescription() {
        this.description
    }

    void setDescription(String description) {
        this.description = description
    }
}
