package com.rentaplace.auth.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rentaplace.auth.jwt.enumeration.CustomJwtClaims;
import org.springframework.stereotype.Component;

@Component
public class JwtClaimUtils {

    public String extractPermissionsClaim(String accessToken) {
        DecodedJWT decodedToken = JWT.decode(accessToken);
        return decodedToken.getClaim(CustomJwtClaims.AUTHORITIES.getValue()).asString();
    }

    public String extractSubjectClaim(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(CustomJwtClaims.SUBJECT.getValue()).asString();
    }
}
