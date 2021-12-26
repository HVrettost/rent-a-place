package com.blueground.auth.jwt.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.blueground.auth.config.properties.JWTConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtVerifierFactory {

    private final JWTConfigProperties jwtConfigProperties;

    public JWTVerifier getJwtVerifier(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfigProperties.getSecret());
        return JWT.require(algorithm)
                .withIssuer(jwtConfigProperties.getIssuer())
                .withSubject(subject)
                .acceptLeeway(jwtConfigProperties.getLeeway())
                .build();
    }
}
