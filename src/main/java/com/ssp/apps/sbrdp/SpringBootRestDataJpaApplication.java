package com.ssp.apps.sbrdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ssp.apps.sbrdp.dto.Employee;
import com.ssp.apps.sbrdp.service.EmployeeService;

@SpringBootApplication
public class SpringBootRestDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired EmployeeService employeeService) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                employeeService.createEmployee(new Employee("Siva", "dummy1@nodomain.com"));
                employeeService.createEmployee(new Employee("Prasad", "dummy2@nodomain.com"));
                employeeService.createEmployee(new Employee("Somarouthu", "dummy3@nodomain.com"));
            }
        };

    }

}
