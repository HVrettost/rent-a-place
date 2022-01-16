package com.rentaplace.auth.service;

import com.rentaplace.auth.model.dto.AuthenticationRequestDto;
import com.rentaplace.auth.validator.UsernameValidator;
import com.rentaplace.common.exception.RentAPlaceCoreException;
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
    public void authenticate(AuthenticationRequestDto authenticationRequest) throws RentAPlaceCoreException {
        usernameValidator.validate(authenticationRequest.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
    }
}
