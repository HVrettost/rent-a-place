package com.blueground.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationBeans {

    @Bean
    Clock utcClock() {
        return Clock.systemUTC();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
