package com.blueground.auth.config;

import com.blueground.auth.filter.AuthorizationFilter;
import com.blueground.auth.jwt.utils.JwtClaimUtils;
import com.blueground.auth.jwt.validator.JwtValidator;
import com.blueground.auth.utils.HeaderUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTH_ACCESS_TOKEN_URI = "marsrental/v1/auth/token/access";
    private static final String AUTH_TEST_ENDPOINTS_WILDCARD_URI = "marsrental/test/**";
    private static final String AUTH_REFRESH_TOKEN_URI = "marsrental/v1/auth/token/refresh";
    private static final String AUTH_REFRESH_TOKEN_ALL_URI = "marsrental/v1/auth/token/all";
    private static final String GET_UNITS_BY_SEARCH_VALUE_URI = "marsrental/v1/units";
    private static final String SAVE_REVIEW_URI = "marsrental/v1/reviews";

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtClaimUtils jwtClaimUtils;
    private final JwtValidator jwtValidator;
    private final ObjectMapper objectMapper;
    private final HeaderUtils headerUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, AUTH_ACCESS_TOKEN_URI).permitAll()
                .antMatchers(HttpMethod.POST, AUTH_TEST_ENDPOINTS_WILDCARD_URI).permitAll()
                .antMatchers(HttpMethod.DELETE, AUTH_TEST_ENDPOINTS_WILDCARD_URI).permitAll()
                .antMatchers(HttpMethod.GET, AUTH_TEST_ENDPOINTS_WILDCARD_URI).permitAll()
                .antMatchers(HttpMethod.DELETE, AUTH_ACCESS_TOKEN_URI).permitAll()
                .antMatchers(HttpMethod.PUT, AUTH_REFRESH_TOKEN_URI).permitAll()
                .antMatchers(HttpMethod.DELETE, AUTH_REFRESH_TOKEN_ALL_URI).permitAll()
                .antMatchers(HttpMethod.GET, GET_UNITS_BY_SEARCH_VALUE_URI).permitAll()
                .antMatchers(HttpMethod.POST, SAVE_REVIEW_URI).permitAll()
                .and()
                .addFilterAfter(new AuthorizationFilter(jwtValidator, jwtClaimUtils, objectMapper, headerUtils), FilterSecurityInterceptor.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
