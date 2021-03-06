package com.rentaplace.auth.api;

import com.rentaplace.auth.model.dto.AuthenticationRequestDto;
import com.rentaplace.common.exception.RentAPlaceCoreException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "rentaplace/v1/auth/token")
public interface AuthTokenApi {


    @PostMapping(value = "access",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> generateAccessToken(HttpServletRequest httpServletRequest,
                                             @RequestBody AuthenticationRequestDto authenticationRequest) throws RentAPlaceCoreException;

    @PutMapping(value = "refresh",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateAccessToken(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException;

    @PreAuthorize("hasAuthority('REFRESH_TOKEN_DELETE')")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> invalidateRefreshToken(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException;

    @PreAuthorize("hasAuthority('REFRESH_TOKEN_ALL_DELETE')")
    @DeleteMapping(value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest) throws RentAPlaceCoreException;

}
