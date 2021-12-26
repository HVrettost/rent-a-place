package com.blueground.auth.validator;

import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.jwt.enumeration.TokenType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenCookieValidator implements Validator<String> {

    @Override
    public void validate(String cookie) throws AuthorizationException {
        if (!StringUtils.hasText(cookie)) {
            throw new AuthorizationException(AuthorizationErrorCodes.TOKEN_COULD_NOT_BE_EXTRACTED);
        }

        List<String> cookieParts = Arrays.stream(cookie.split("; "))
                .collect(Collectors.toList());

        if (!accessTokenExists(cookieParts) || !refreshTokenExists(cookieParts)) {
            throw new AuthorizationException(AuthorizationErrorCodes.TOKEN_COULD_NOT_BE_EXTRACTED);
        }
    }

    private boolean accessTokenExists(List<String> cookieParts) {
        return cookieParts.stream().anyMatch(part -> part.contains(TokenType.ACCESS.getValue()));
    }

    private boolean refreshTokenExists(List<String> cookieParts) {
        return cookieParts.stream().anyMatch(part -> part.contains(TokenType.REFRESH.getValue()));
    }
}
