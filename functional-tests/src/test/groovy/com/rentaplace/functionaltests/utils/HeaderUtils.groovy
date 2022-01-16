package com.rentaplace.functionaltests.utils

import org.springframework.http.HttpHeaders

import javax.servlet.http.Cookie

trait HeaderUtils {

    HttpHeaders createHeadersWithoutCookie(String userAgent) {
        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.set('User-Agent', userAgent)

        httpHeaders
    }

    HttpHeaders createHeadersWithCookie(def cookie, String userAgent) {
        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.set('User-Agent', userAgent)
        httpHeaders.set('Cookie', cookie[0] + ' ' + cookie[1])

        httpHeaders
    }
}
