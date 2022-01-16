package com.rentaplace.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtils {

    public String createAccessTokenCookie(String accessToken) {
        return accessTokenFlag(accessToken);
    }

    public String createRefreshTokenCookie(String refreshToken) {
        return refreshTokenFlag(refreshToken);
    }

    private String accessTokenFlag(String accessToken) {
        return "accessToken=" + accessToken + "; ";
    }

    private String refreshTokenFlag(String refreshToken) {
        return "refreshToken=" + refreshToken + ";";
    }

}
