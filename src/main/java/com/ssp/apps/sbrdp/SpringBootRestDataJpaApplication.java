package com.ssp.apps.sbrdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.service.UserService;

@SpringBootApplication
public class SpringBootRestDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired UserService userService) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                userService.createUser(new User("Siva", "dummy1@nodomain.com"));
                userService.createUser(new User("Prasad", "dummy2@nodomain.com"));
                userService.createUser(new User("Somarouthu", "dummy3@nodomain.com"));
            }
        };

    }

}
