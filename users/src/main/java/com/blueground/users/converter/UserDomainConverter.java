package com.blueground.users.converter;

import com.blueground.users.model.domain.UserDomain;
import com.blueground.users.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDomainConverter implements Converter<User, UserDomain> {

    private final ModelMapper modelMapper;

    @Override
    public UserDomain convert(User user) {
        return modelMapper.map(user, UserDomain.class);
    }
}
