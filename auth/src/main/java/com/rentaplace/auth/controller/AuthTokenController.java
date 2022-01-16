package com.rentaplace.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rentaplace.auth.api.AuthTokenApi;
import com.rentaplace.auth.model.dto.AuthenticationRequestDto;
import com.rentaplace.auth.service.AuthTokenService;
import com.rentaplace.auth.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthTokenController implements AuthTokenApi {

    private final AuthenticationService authenticationService;
    private final AuthTokenService authTokenService;

    @Override
    public ResponseEntity<Void> generateAccessToken(HttpServletRequest httpServletRequest,
                                                    AuthenticationRequestDto authenticationRequest) throws RentAPlaceCoreException {
        authenticationService.authenticate(authenticationRequest);
        HttpHeaders httpHeaders = authTokenService.createCookieHeadersForAuthorization(httpServletRequest, authenticationRequest.getUsername());

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateAccessToken(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException {
        HttpHeaders httpHeaders = authTokenService.updateCookieHeaderForAccessToken(httpServletRequest);
        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> invalidateRefreshToken(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException {
        authTokenService.invalidateRefreshToken(httpServletRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException {
        authTokenService.invalidateRefreshTokensByUsername(httpServletRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
