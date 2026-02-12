package com.chaoslab.chaoslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChaoslabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaoslabApplication.class, args);
    }
}
