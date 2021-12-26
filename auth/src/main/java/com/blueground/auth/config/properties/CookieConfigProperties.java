package com.blueground.auth.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "marsrental.auth.cookie")
@PropertySource("classpath:${spring.profiles.active}/cookie-${spring.profiles.active}.properties")
@Getter
@Setter
public class CookieConfigProperties {

    private String path;
    private String httpOnly;
    private String domain;
    private String sameSite;
    private String secure;
}
