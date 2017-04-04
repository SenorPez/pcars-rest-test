package com.senorpez.projectcars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        CreateDatabase.main(args);
        SpringApplication.run(Application.class, args);
    }
}
