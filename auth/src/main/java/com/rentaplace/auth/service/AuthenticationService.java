package com.rentaplace.auth.service;

import com.rentaplace.auth.exception.AuthenticationException;
import com.rentaplace.auth.model.dto.AuthenticationRequestDto;
import com.rentaplace.common.exception.RentAPlaceCoreException;

public interface AuthenticationService {

    void authenticate(AuthenticationRequestDto authenticationRequest) throws AuthenticationException, RentAPlaceCoreException;
}
