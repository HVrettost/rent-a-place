package com.blueground.auth.mapper;

import com.blueground.auth.domain.AuthRoleToUsernameDomain;
import com.blueground.auth.model.entity.AuthRoleToUsername;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthRoleToUsernameMapper implements DtoMapper<AuthRoleToUsernameDomain, AuthRoleToUsername>,
        EntityMapper<AuthRoleToUsername, AuthRoleToUsernameDomain> {

    private final ModelMapper modelMapper;

    public AuthRoleToUsernameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthRoleToUsernameDomain toDto(AuthRoleToUsername authRoleToUsername) {
        return modelMapper.map(authRoleToUsername, AuthRoleToUsernameDomain.class);
    }

    @Override
    public AuthRoleToUsername toEntity(AuthRoleToUsernameDomain authRoleToUsernameDto) {
        return modelMapper.map(authRoleToUsernameDto, AuthRoleToUsername.class);
    }
}
