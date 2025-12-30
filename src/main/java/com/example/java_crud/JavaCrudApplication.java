package com.example.java_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaCrudApplication {
    public static void main(String[] args) {
        System.out.println("server is started....");
        SpringApplication.run(JavaCrudApplication.class, args);
    }
}
