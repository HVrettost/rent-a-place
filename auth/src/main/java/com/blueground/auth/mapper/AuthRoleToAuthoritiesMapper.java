package com.blueground.auth.mapper;

import com.blueground.auth.domain.AuthRoleToAuthoritiesDomain;
import com.blueground.auth.model.entity.AuthRoleToAuthorities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthRoleToAuthoritiesMapper implements DtoMapper<AuthRoleToAuthoritiesDomain, AuthRoleToAuthorities>,
        EntityMapper<AuthRoleToAuthorities, AuthRoleToAuthoritiesDomain> {

    private final ModelMapper modelMapper;

    public AuthRoleToAuthoritiesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthRoleToAuthoritiesDomain toDto(AuthRoleToAuthorities authRoleToAuthorities) {
        return modelMapper.map(authRoleToAuthorities, AuthRoleToAuthoritiesDomain.class);
    }

    @Override
    public AuthRoleToAuthorities toEntity(AuthRoleToAuthoritiesDomain authRoleToAuthoritiesDto) {
        return modelMapper.map(authRoleToAuthoritiesDto, AuthRoleToAuthorities.class);
    }
}
