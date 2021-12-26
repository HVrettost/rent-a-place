package com.blueground.auth.service;

import com.blueground.auth.dao.AuthRoleToPermissionsDao;
import com.blueground.auth.dao.AuthRoleToUsernameDao;
import com.blueground.auth.dao.RefreshTokenWhitelistDao;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.jwt.utils.JwtClaimUtils;
import com.blueground.auth.jwt.utils.JwtGeneratorUtils;
import com.blueground.auth.model.entity.RefreshTokenWhitelist;
import com.blueground.auth.utils.CookieUtils;
import com.blueground.auth.utils.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class DefaultAuthTokenService implements AuthTokenService {

    private final HeaderUtils headerUtils;
    private final JwtGeneratorUtils jwtGeneratorUtils;
    private final CookieUtils cookieUtils;
    private final JwtClaimUtils jwtClaimUtils;
    private final RefreshTokenWhitelistDao refreshTokenWhitelistDao;
    private final AuthRoleToUsernameDao authRoleToUsernameDao;
    private final AuthRoleToPermissionsDao authRoleToPermissionsDao;

    @Override
    public HttpHeaders createCookieHeadersForAuthorization(HttpServletRequest httpServletRequest, String username) throws AuthorizationException {
        String userAgent = headerUtils.extractUserAgent(httpServletRequest);
        String permissions = getUserPermissions(username);
        String accessToken = jwtGeneratorUtils.generateAccessToken(username, permissions);
        String refreshToken = jwtGeneratorUtils.generateRefreshToken(username);

        invalidateRefreshTokenIfExistsAndSaveNew(refreshToken, username, userAgent);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createAccessTokenCookie(accessToken));
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createRefreshTokenCookie(refreshToken));

        return httpHeaders;
    }

    private String getUserPermissions(String username) throws AuthorizationException {
        String authRole = authRoleToUsernameDao.getAuthRoleByUsername(username).getRole();
        return authRoleToPermissionsDao.getPermissionsByAuthRole(authRole).getAuthorities();
    }

    private void invalidateRefreshTokenIfExistsAndSaveNew(String refreshToken, String username, String userAgent) {
        if (refreshTokenWhitelistDao.isRefreshTokenExistsByUsernameAndUserAgent(username, userAgent)) {
            refreshTokenWhitelistDao.invalidateRefreshToken(username, userAgent);
        }

        refreshTokenWhitelistDao.saveRefreshToken(refreshToken, username, userAgent);
    }


    @Override
    public HttpHeaders updateCookieHeaderForAccessToken(HttpServletRequest httpServletRequest) throws AuthorizationException {
        String refreshToken = headerUtils.extractRefreshToken(httpServletRequest);
        String username = jwtClaimUtils.extractSubjectClaim(refreshToken);
        String permissions = getUserPermissions(username);
        String accessToken = jwtGeneratorUtils.generateAccessToken(username, permissions);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createRefreshTokenCookie(refreshToken));
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createAccessTokenCookie(accessToken));

        return httpHeaders;
    }

    @Override
    public void invalidateRefreshToken(HttpServletRequest httpServletRequest) throws AuthorizationException {
        String accessToken = headerUtils.extractAccessToken(httpServletRequest);
        String userAgent = headerUtils.extractUserAgent(httpServletRequest);
        String username = jwtClaimUtils.extractSubjectClaim(accessToken);

        if (refreshTokenWhitelistDao.isRefreshTokenExistsByUsernameAndUserAgent(username, userAgent)) {
            refreshTokenWhitelistDao.invalidateRefreshToken(username, userAgent);
        } else {
            throw new AuthorizationException(AuthorizationErrorCodes.REFRESH_TOKEN_NOT_FOUND);
        }
    }

    @Override
    public void invalidateRefreshTokensByUsername(HttpServletRequest httpServletRequest) throws AuthorizationException {
        String accessToken = headerUtils.extractAccessToken(httpServletRequest);
        String username = jwtClaimUtils.extractSubjectClaim(accessToken);

        if (refreshTokenWhitelistDao.isRefreshTokenExistsByUsername(username)) {
            refreshTokenWhitelistDao.invalidateRefreshTokensByUsername(username);
        } else {
            throw new AuthorizationException(AuthorizationErrorCodes.REFRESH_TOKENS_NOT_FOUND);
        }
    }
}