package com.example.testtaskclearsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestTaskClearSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestTaskClearSolutionsApplication::main).with(TestTestTaskClearSolutionsApplication.class).run(args);
    }

}
