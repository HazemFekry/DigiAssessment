package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemDesignMain {
    public static void main(String[] args) {
        DBCreation.createDBIfNotExist();
        SpringApplication.run(SystemDesignMain.class, args);
    }
}