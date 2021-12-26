package com.blueground.auth.service;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.model.dto.AuthenticationRequestDto;

public interface AuthenticationService {

    void authenticate(AuthenticationRequestDto authenticationRequest) throws AuthenticationException;
}
