package com.rentaplace.auth.service;

import com.rentaplace.auth.exception.AuthorizationException;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface AuthTokenService {

    HttpHeaders createCookieHeadersForAuthorization(HttpServletRequest httpServletRequest, String username) throws AuthorizationException;

    HttpHeaders updateCookieHeaderForAccessToken(HttpServletRequest httpServletRequest) throws AuthorizationException;

    void invalidateRefreshToken(HttpServletRequest httpServletRequest) throws AuthorizationException;

    void invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest) throws AuthorizationException;
}
