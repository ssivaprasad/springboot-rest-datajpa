package com.ssp.apps.sbrdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBootRestDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDataJpaApplication.class, args);
    }

}
