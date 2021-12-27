package com.blueground.auth.utils;

import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.jwt.enumeration.TokenType;
import com.blueground.auth.validator.TokenCookieValidator;
import com.blueground.auth.validator.Validator;
import com.blueground.common.exception.MarsRentalCoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class HeaderUtils {

    private final TokenCookieValidator tokenCookieValidator;

    public String extractUserAgent(HttpServletRequest request) throws AuthorizationException {
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        if (userAgent == null || "".equals(userAgent)) {
            throw new AuthorizationException(AuthorizationErrorCodes.USER_AGENT_NOT_FOUND);
        }

        return userAgent;
    }

    public String extractAccessToken(HttpServletRequest request) throws AuthorizationException {
        return extractToken(request, TokenType.ACCESS.getValue());
    }

    public String extractRefreshToken(HttpServletRequest request) throws AuthorizationException  {
        return extractToken(request, TokenType.REFRESH.getValue());
    }

    private String extractToken(HttpServletRequest request, String tokenType) throws AuthorizationException {
        String cookie = request.getHeader(HttpHeaders.COOKIE);
        tokenCookieValidator.validate(cookie);

        String token = Arrays.stream(cookie.split("; "))
                             .filter(cookiePart -> cookiePart.contains(tokenType))
                             .findFirst()
                             .get();


        return token.replace(tokenType + "=", "");
    }
}
