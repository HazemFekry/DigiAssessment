package com.assessment;

import com.assessment.permission.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemDesignMain {
    public static void main(String[] args) {
        DBCreation.createDBIfNotExist();
        SpringApplication.run(SystemDesignMain.class, args);
    }

    @Bean
    public CommandLineRunner persistSampleData(PermissionGroupRepository permissionGroupRepository, PermissionRepository permissionRepository) {
        return (args) -> {

            PermissionGroup permissionGroup1 = permissionGroupRepository.saveAndFlush(new PermissionGroup(50, "Admin"));
            PermissionGroup permissionGroup2 = permissionGroupRepository.saveAndFlush(new PermissionGroup(51, "notAdmin"));

            permissionRepository.save(new Permission(21L, "edit@mail.com", PermissionLevel.EDIT, permissionGroup1));
            permissionRepository.save(new Permission(22L, "view@mail.com", PermissionLevel.VIEW, permissionGroup1));
            permissionRepository.save(new Permission(23L, "notAdmin@mail.com", PermissionLevel.EDIT, permissionGroup2));
            System.out.println("basic data inserted.2");

        };
    }
}