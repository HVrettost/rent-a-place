package com.blueground.auth.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "marsrental.auth.jwt")
@PropertySource("classpath:${spring.profiles.active}/jwt-${spring.profiles.active}.properties")
@Getter
@Setter
public class JWTConfigProperties {

    private String issuer;
    private String secret;
    private long accessTokenExpirationIntervalInMillis;
    private long refreshTokenExpirationIntervalInMillis;
    private long leeway;
}
