package com.ssp.apps.sbrdp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssp.apps.sbrdp.entity.Employee;
import com.ssp.apps.sbrdp.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Optional.ofNullable(employees).orElseGet(() -> new ArrayList<Employee>());

        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployee(id);
    }


    @GetMapping("/pagination")
    public String getEmployees(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(name = "order", defaultValue = "ASC", required = false) String order) {
        return String.format(
                "== >> Get All employees called with pagination: page: %s, limit: %s, order: %s",
                page, limit, order);
    }
}
