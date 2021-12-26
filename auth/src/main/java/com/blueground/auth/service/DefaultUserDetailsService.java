package com.blueground.auth.service;

import com.blueground.users.dao.UsersDao;
import com.blueground.users.exception.UsersException;
import com.blueground.users.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto user = usersDao.getUserByUsername(username);
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        } catch (UsersException ex) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
    }
}
