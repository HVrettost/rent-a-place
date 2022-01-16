package com.rentaplace.auth.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rentaplace.auth.config.properties.JWTConfigProperties;
import com.rentaplace.auth.jwt.enumeration.CustomJwtClaims;
import com.rentaplace.auth.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class JwtGeneratorUtils {

    private final JWTConfigProperties jwtConfigProperties;
    private final DateUtils dateUtils;

    public JwtGeneratorUtils(JWTConfigProperties jwtConfigProperties, DateUtils dateUtils) {
        this.jwtConfigProperties = jwtConfigProperties;
        this.dateUtils = dateUtils;
    }

    public String generateAccessToken(String username, String permissions) {
        return JWT.create()
                .withIssuer(jwtConfigProperties.getIssuer())
                .withIssuedAt(dateUtils.getCurrentUTCDate())
                .withExpiresAt(dateUtils.getCurrentUTCDateWithOffset(jwtConfigProperties.getAccessTokenExpirationIntervalInMillis()))
                .withSubject(username)
                .withClaim(CustomJwtClaims.AUTHORITIES.getValue(), permissions)
                .sign(Algorithm.HMAC256(jwtConfigProperties.getSecret()));
    }

    public String generateRefreshToken(String username) {
        return JWT.create()
                .withIssuer(jwtConfigProperties.getIssuer())
                .withIssuedAt(dateUtils.getCurrentUTCDate())
                .withExpiresAt(dateUtils.getCurrentUTCDateWithOffset(jwtConfigProperties.getRefreshTokenExpirationIntervalInMillis()))
                .withSubject(username)
                .sign(Algorithm.HMAC256(jwtConfigProperties.getSecret()));
    }
}
