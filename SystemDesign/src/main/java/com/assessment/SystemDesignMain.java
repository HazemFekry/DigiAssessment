package com.assessment;

import com.assessment.permission.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemDesignMain {
    public static void main(String[] args) {
        DBCreation.createDBIfNotExist();
        SpringApplication.run(SystemDesignMain.class, args);
    }
}