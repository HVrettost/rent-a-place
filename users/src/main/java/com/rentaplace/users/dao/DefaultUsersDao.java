package com.rentaplace.users.dao;

import com.rentaplace.users.exception.UsersException;
import com.rentaplace.users.exception.error.UsersErrorCodes;
import com.rentaplace.users.model.domain.UserDomain;
import com.rentaplace.users.model.entity.User;
import com.rentaplace.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUsersDao implements UsersDao {

    private final UsersRepository usersRepository;
    private final Converter<User, UserDomain> converter;

    @Override
    public UserDomain getUserByUsername(String username) throws UsersException {
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsersException(UsersErrorCodes.USER_DOES_NOT_EXIST));

        return converter.convert(user);
    }
}
