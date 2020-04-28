package com.ssp.apps.sbrdp.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ssp.apps.sbrdp.SpringBootRestDataJpaApplication;
import com.ssp.apps.sbrdp.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestDataJpaApplication.class)
@Slf4j
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        employees.stream().forEach(employee -> log.info("== >> USer Details: {}", employee));
    }

}
