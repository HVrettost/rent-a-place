package com.blueground.auth.api;

import com.blueground.auth.model.dto.AuthenticationRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "marsrental/v1/auth/token")
public interface AuthTokenApi {


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> generateAccessToken(HttpServletRequest httpServletRequest,
                                             @RequestBody AuthenticationRequestDto authenticationRequest);

    @PutMapping(value = "/refresh",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateAccessToken(HttpServletRequest httpServletRequest);

    @PreAuthorize("hasAuthority('REFRESH_TOKEN_DELETE')")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> invalidateRefreshToken(HttpServletRequest httpServletRequest);

    @PreAuthorize("hasAuthority('REFRESH_TOKEN_ALL_DELETE')")
    @DeleteMapping(value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest);

}
