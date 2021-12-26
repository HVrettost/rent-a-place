package com.blueground.auth.validator;

import com.blueground.auth.exception.AuthenticationException;
import com.blueground.auth.exception.error.AuthenticationErrorCodes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UsernameValidator implements Validator<String> {

    private static final int USERNAME_ACCEPTABLE_LENGTH = 10;

    @Override
    public void validate(String username) throws AuthenticationException {
        if (!StringUtils.hasText(username) || username.length() > USERNAME_ACCEPTABLE_LENGTH)  {
            throw new AuthenticationException(AuthenticationErrorCodes.BAD_CREDENTIALS);
        }
    }
}
