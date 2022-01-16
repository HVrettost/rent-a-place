package com.rentaplace.functionaltests.exception

class HttpErrorResponse {

    private int rawHttpStatus
    private String errorResponseAsString

    HttpErrorResponse(int rawHttpStatus, String errorResponseAsString) {
        this.rawHttpStatus = rawHttpStatus
        this.errorResponseAsString = errorResponseAsString
    }

    int getRawHttpStatus() {
        this.rawHttpStatus
    }

    void setRawHttpStatus(int rawHttpStatus) {
        this.rawHttpStatus = rawHttpStatus
    }

    String getErrorResponseAsString() {
        this.errorResponseAsString
    }

    void setErrorResponseAsString(String errorResponseAsString) {
        this.errorResponseAsString = errorResponseAsString
    }

}
