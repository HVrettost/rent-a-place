package com.blueground.auth.dao;

import com.blueground.auth.mapper.RefreshTokenWhitelistMapper;
import com.blueground.auth.model.entity.RefreshTokenWhitelist;
import com.blueground.auth.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultRefreshTokenWhitelistDao implements RefreshTokenWhitelistDao {

    private final AuthTokenRepository authTokenRepository;
    private final RefreshTokenWhitelistMapper refreshTokenWhitelistMapper;

    @Override
    public void saveRefreshToken(String refreshToken, String username, String userAgent) {
        RefreshTokenWhitelist refreshTokenWhitelist = refreshTokenWhitelistMapper.toEntity(refreshToken, username, userAgent);
        authTokenRepository.save(refreshTokenWhitelist);
    }

    @Override
    public void invalidateRefreshToken(String username, String userAgent) {
        authTokenRepository.deleteByUsernameAndUserAgent(username, userAgent);
    }

    @Override
    public void invalidateRefreshTokensByUsername(String username) {
        authTokenRepository.deleteAllByUsername(username);
    }

    @Override
    public boolean isRefreshTokenExistsByUsername(String username) {
        return authTokenRepository.existsByUsername(username);
    }

    @Override
    public boolean isRefreshTokenExistsByUsernameAndUserAgent(String username, String userAgent) {
        return authTokenRepository.existsByUsernameAndUserAgent(username, userAgent);
    }
}
