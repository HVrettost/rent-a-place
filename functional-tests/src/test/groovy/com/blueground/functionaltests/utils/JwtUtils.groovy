package com.blueground.functionaltests.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT

trait JwtUtils {

    private static final String SECRET = 'f4f2f2d8a13764301b7cd571fe1a61cd4a4a9a66ac21eb69c314284d040ae8e4'
    private static final String ISSUER = 'd20b71e8035d4f300afde013a6e8dd8d7e8951695fe9658658884934ec3550e7'

    void verifyToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token)

        Algorithm algorithm = Algorithm.HMAC256(SECRET)
        JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withSubject(decodedJWT.getSubject())
                .acceptLeeway(5L)
                .build()
    }

}
