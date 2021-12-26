package com.blueground.auth.service;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.model.dto.AuthenticationRequestDto;
import com.blueground.auth.validator.UsernameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsernameValidator usernameValidator;

    @Override
    public void authenticate(AuthenticationRequestDto authenticationRequest) throws AuthenticationException {
        usernameValidator.validate(authenticationRequest.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
    }
}
