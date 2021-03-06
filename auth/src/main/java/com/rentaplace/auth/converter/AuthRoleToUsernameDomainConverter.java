package com.rentaplace.auth.converter;

import com.rentaplace.auth.domain.AuthRoleToUsernameDomain;
import com.rentaplace.auth.model.entity.AuthRoleToUsername;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthRoleToUsernameDomainConverter implements Converter<AuthRoleToUsername, AuthRoleToUsernameDomain> {

    private final ModelMapper modelMapper;

    @Override
    public AuthRoleToUsernameDomain convert(AuthRoleToUsername authRoleToUsername) {
        return modelMapper.map(authRoleToUsername, AuthRoleToUsernameDomain.class);
    }
}
