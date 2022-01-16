package com.rentaplace.functionaltests.requests

import com.rentaplace.functionaltests.utils.HeaderUtils

interface Request extends HeaderUtils {

    static final String HOST = 'http://localhost:8900'

    def execute();

}