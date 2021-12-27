package com.blueground.functionaltests.requests

import com.blueground.functionaltests.utils.HeaderUtils

interface Request extends HeaderUtils {

    static final String HOST = 'http://localhost:8900'

    def execute();

}