package com.blueground.functionaltests.requests

interface Request {

    static final String HOST = 'http://localhost:8080'

    def execute();

}