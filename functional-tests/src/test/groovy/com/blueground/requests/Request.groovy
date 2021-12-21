package com.blueground.requests

interface Request {

    static final String HOST = 'http://localhost:8080'

    def execute();

}