package com.rentaplace.functionaltests.exception

class ErrorDetails {

    private int applicationErrorCode
    private String description

    ErrorDetails(int applicationErrorCode, String description) {
        this.applicationErrorCode = applicationErrorCode
        this.description = description
    }

    ErrorDetails() {

    }

    int getApplicationErrorCode() {
        this.applicationErrorCode
    }

    void setApplicationErrorCode(int errorCode) {
        this.applicationErrorCode = errorCode
    }

    String getDescription() {
        this.description
    }

    void setDescription(String description) {
        this.description = description
    }
}
