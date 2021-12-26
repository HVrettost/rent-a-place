package com.blueground.functionaltests.requests

import com.blueground.functionaltests.utils.HeaderUtils

interface Request extends HeaderUtils {

    static final String HOST = 'http://localhost:8080'

    def execute();

}