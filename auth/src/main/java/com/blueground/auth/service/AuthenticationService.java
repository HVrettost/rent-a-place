package com.blueground.auth.service;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.model.dto.AuthenticationRequestDto;
import com.blueground.common.exception.RentAPlaceCoreException;

public interface AuthenticationService {

    void authenticate(AuthenticationRequestDto authenticationRequest) throws AuthenticationException, RentAPlaceCoreException;
}
