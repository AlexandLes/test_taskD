package com.example.testtaskclearsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TestTaskClearSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskClearSolutionsApplication.class, args);
    }
}

