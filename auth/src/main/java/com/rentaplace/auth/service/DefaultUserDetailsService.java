package com.rentaplace.auth.service;

import com.rentaplace.users.dao.UsersDao;
import com.rentaplace.users.exception.UsersException;
import com.rentaplace.users.model.domain.UserDomain;
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
            UserDomain user = usersDao.getUserByUsername(username);

            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        } catch (UsersException ex) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
    }
}
