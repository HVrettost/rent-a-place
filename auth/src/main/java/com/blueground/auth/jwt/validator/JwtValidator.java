package com.blueground.auth.jwt.validator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.jwt.factory.JwtVerifierFactory;
import com.blueground.auth.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator implements Validator<String> {

    private final JwtVerifierFactory jwtVerifierFactory;

    @Override
    public void validate(String token) throws AuthorizationException {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            jwtVerifierFactory.getJwtVerifier(decodedJWT.getSubject()).verify(decodedJWT);
        } catch (TokenExpiredException tokenExpiredException) {
            throw new AuthorizationException(AuthorizationErrorCodes.TOKEN_EXPIRED);
        } catch (JWTVerificationException jwtVerificationException) {
            throw new AuthorizationException(AuthorizationErrorCodes.INVALID_TOKEN);
        }
    }
}
