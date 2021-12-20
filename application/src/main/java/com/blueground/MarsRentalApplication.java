package com.blueground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.blueground")
public class MarsRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarsRentalApplication.class, args);
    }
}
