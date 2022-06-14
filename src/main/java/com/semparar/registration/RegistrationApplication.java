package com.semparar.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RegistrationApplication implements CommandLineRunner{

    public static void main(String[] args) {

        SpringApplication.run(RegistrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
