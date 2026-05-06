package org.example.tiendalemlah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example.tiendalemlah")
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}