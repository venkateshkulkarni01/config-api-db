package com.example.configapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "com.example.configapi"
)
public class ConfigApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApiApplication.class, args);
    }
}