package com.blueground.auth.converter;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.model.entity.AuthRoleToUsername;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthRoleToUsernameConverter implements Converter<AuthRoleToUsernameDomain, AuthRoleToUsername> {

    private final ModelMapper modelMapper;

    @Override
    public AuthRoleToUsername convert(AuthRoleToUsernameDomain authRoleToUsernameDomain) {
        return modelMapper.map(authRoleToUsernameDomain, AuthRoleToUsername.class);
    }
}
