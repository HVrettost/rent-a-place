package com.blueground.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.blueground.auth.api.AuthTokenApi;
import com.blueground.auth.model.dto.AuthenticationRequestDto;
import com.blueground.auth.service.AuthTokenService;
import com.blueground.auth.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthTokenController implements AuthTokenApi {

    private final AuthenticationService authenticationService;
    private final AuthTokenService authTokenService;

    @Override
    public ResponseEntity<Void> generateAccessToken(HttpServletRequest httpServletRequest,
                                                    AuthenticationRequestDto authenticationRequest) throws AuthenticationException, AuthorizationException {
        authenticationService.authenticate(authenticationRequest);
        HttpHeaders httpHeaders = authTokenService.createCookieHeadersForAuthorization(httpServletRequest, authenticationRequest.getUsername());

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateAccessToken(HttpServletRequest httpServletRequest) throws AuthorizationException {
        HttpHeaders httpHeaders = authTokenService.updateCookieHeaderForAccessToken(httpServletRequest);
        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> invalidateRefreshToken(HttpServletRequest httpServletRequest) throws AuthorizationException {
        authTokenService.invalidateRefreshToken(httpServletRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest) throws AuthorizationException {
        authTokenService.invalidateRefreshTokensByUsername(httpServletRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
