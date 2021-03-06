package com.rentaplace.auth.converter;

import com.rentaplace.auth.domain.AuthRoleToAuthoritiesDomain;
import com.rentaplace.auth.model.entity.AuthRoleToAuthorities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthRoleToAuthoritiesConverter implements Converter<AuthRoleToAuthoritiesDomain, AuthRoleToAuthorities> {

    private final ModelMapper modelMapper;

    @Override
    public AuthRoleToAuthorities convert(AuthRoleToAuthoritiesDomain authRoleToAuthoritiesDomain) {
        return modelMapper.map(authRoleToAuthoritiesDomain, AuthRoleToAuthorities.class);
    }
}
