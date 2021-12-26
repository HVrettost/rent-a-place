package com.blueground.auth.mapper;

import com.blueground.auth.model.entity.RefreshTokenWhitelist;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenWhitelistMapper {

    public RefreshTokenWhitelist toEntity(String refreshToken, String username, String userAgent) {
        RefreshTokenWhitelist refreshTokenWhitelist = new RefreshTokenWhitelist();
        refreshTokenWhitelist.setUsername(username);
        refreshTokenWhitelist.setRefreshToken(refreshToken);
        refreshTokenWhitelist.setUserAgent(userAgent);

        return refreshTokenWhitelist;
    }
}
