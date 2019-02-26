package com.inpivota.omega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmegaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmegaApplication.class, args);
    }
}
