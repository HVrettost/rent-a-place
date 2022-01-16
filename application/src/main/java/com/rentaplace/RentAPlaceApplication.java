package com.rentaplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rentaplace")
public class RentAPlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentAPlaceApplication.class, args);
    }
}
