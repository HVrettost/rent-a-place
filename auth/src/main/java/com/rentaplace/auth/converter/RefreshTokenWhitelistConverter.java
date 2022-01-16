package com.rentaplace.auth.converter;

import com.rentaplace.auth.model.entity.RefreshTokenWhitelist;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenWhitelistConverter {

    public RefreshTokenWhitelist convert(String refreshToken, String username, String userAgent) {
        RefreshTokenWhitelist refreshTokenWhitelist = new RefreshTokenWhitelist();
        refreshTokenWhitelist.setUsername(username);
        refreshTokenWhitelist.setRefreshToken(refreshToken);
        refreshTokenWhitelist.setUserAgent(userAgent);

        return refreshTokenWhitelist;
    }
}
