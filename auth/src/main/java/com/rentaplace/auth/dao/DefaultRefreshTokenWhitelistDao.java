package com.rentaplace.auth.dao;

import com.rentaplace.auth.converter.RefreshTokenWhitelistConverter;
import com.rentaplace.auth.model.entity.RefreshTokenWhitelist;
import com.rentaplace.auth.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultRefreshTokenWhitelistDao implements RefreshTokenWhitelistDao {

    private final AuthTokenRepository authTokenRepository;
    private final RefreshTokenWhitelistConverter refreshTokenWhitelistMapper;

    @Override
    public void saveRefreshToken(String refreshToken, String username, String userAgent) {
        RefreshTokenWhitelist refreshTokenWhitelist = refreshTokenWhitelistMapper.convert(refreshToken, username, userAgent);
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
