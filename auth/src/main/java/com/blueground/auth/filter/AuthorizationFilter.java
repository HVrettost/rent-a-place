package com.blueground.auth.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.blueground.auth.exception.AuthorizationException;
import com.blueground.auth.exception.error.AuthorizationErrorCodes;
import com.blueground.auth.jwt.utils.JwtClaimUtils;
import com.blueground.auth.jwt.validator.JwtValidator;
import com.blueground.auth.utils.HeaderUtils;
import com.blueground.common.exception.error.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;


import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private static final String ACCESS_TOKEN_ENDPOINT = "/marsrental/v1/auth/token/access";
    private static final String REFRESH_TOKEN_ENDPOINT = "/marsrental/v1/token/refresh";

    private final JwtValidator jwtValidator;
    private final JwtClaimUtils jwtClaimUtils;
    private final ObjectMapper objectMapper;
    private final HeaderUtils headerUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!httpRequest.getRequestURI().startsWith("/marsrental/v")
                || httpRequest.getRequestURI().startsWith("/marsrental/test/v")
                || isAuthenticationRequest(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            if (isRefreshAccessTokenRequest(httpRequest)) {
                String refreshToken = headerUtils.extractRefreshToken(httpRequest);
                jwtValidator.validate(refreshToken);
                chain.doFilter(request, response);
                return;
            }

            String accessToken = headerUtils.extractAccessToken(httpRequest);
            jwtValidator.validate(accessToken);
            String permissions = jwtClaimUtils.extractPermissionsClaim(accessToken);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(null, null, getUserGrantedAuthorities(permissions)));
        } catch (AuthorizationException authorizationException) {
            log.error(authorizationException.getAuthorizationErrorCodes().getDescription(), authorizationException);
            setErrorDetailsAndStatusInServletResponse(
                    httpResponse,
                    authorizationException.getAuthorizationErrorCodes().getHttpStatus(),
                    new ErrorDetails(
                            authorizationException.getAuthorizationErrorCodes().getApplicationErrorCode(),
                            authorizationException.getAuthorizationErrorCodes().getDescription()
                    )
            );
            return;
        } catch (JWTVerificationException jwtVerificationException) {
            log.error("Error verifying JWT token", jwtVerificationException);
            setErrorDetailsAndStatusInServletResponse(
                    httpResponse,
                    AuthorizationErrorCodes.INVALID_TOKEN.getHttpStatus(),
                    new ErrorDetails(
                            AuthorizationErrorCodes.INVALID_TOKEN.getApplicationErrorCode(),
                            AuthorizationErrorCodes.INVALID_TOKEN.getDescription()
                    )
            );
            return;
        } catch (Exception exception) {
            log.error(AuthorizationErrorCodes.GENERIC_AUTHORIZATION_ERROR.getDescription(), exception);
            setErrorDetailsAndStatusInServletResponse(
                    httpResponse,
                    AuthorizationErrorCodes.GENERIC_AUTHORIZATION_ERROR.getHttpStatus(),
                    new ErrorDetails(
                            AuthorizationErrorCodes.GENERIC_AUTHORIZATION_ERROR.getApplicationErrorCode(),
                            AuthorizationErrorCodes.GENERIC_AUTHORIZATION_ERROR.getDescription()
                    )
            );
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isRefreshAccessTokenRequest(HttpServletRequest request) {
        return HttpMethod.PUT.name().equals(request.getMethod()) && REFRESH_TOKEN_ENDPOINT.equals(request.getRequestURI());
    }

    private boolean isAuthenticationRequest(HttpServletRequest request) {
        return HttpMethod.POST.name().equals(request.getMethod()) && ACCESS_TOKEN_ENDPOINT.equals(request.getRequestURI());
    }

    private List<GrantedAuthority> getUserGrantedAuthorities(String authorities) throws AuthorizationException {
        if (!StringUtils.hasText(authorities)) {
            throw new AuthorizationException(AuthorizationErrorCodes.GRANTED_AUTHORITIES_NOT_FOUND);
        }

        return List.of(authorities.split(" "))
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    private void setErrorDetailsAndStatusInServletResponse(HttpServletResponse httpResponse,
                                                           HttpStatus httpStatus,
                                                           ErrorDetails errorDetails) throws IOException {
        httpResponse.setStatus(httpStatus.value());
        httpResponse.getOutputStream().write(objectMapper.writeValueAsBytes(errorDetails));
    }
}
