package com.ssp.apps.sbrdp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.apps.sbrdp.dao.EmployeeRepository;
import com.ssp.apps.sbrdp.dto.Employee;
import com.ssp.apps.sbrdp.exception.DuplicateEmployeeException;
import com.ssp.apps.sbrdp.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        Optional<Employee> retriedEmployee = employeeRepository.findByName(employee.getName());
        retriedEmployee.ifPresent((tempEmp) -> {
            throw new DuplicateEmployeeException();
        });

        employee.setId(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        Optional<Employee> retriedEmployee = employeeRepository.findById(employee.getId());
        retriedEmployee.orElseThrow(() -> new EmployeeNotFoundException());

        employeeRepository.save(employee);
    }


    public void deleteEmployee(String employeeId) {
        Optional<Employee> retriedEmployee = employeeRepository.findById(employeeId);
        retriedEmployee.orElseThrow(() -> new EmployeeNotFoundException());

        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployee(String employeeId) {
        Optional<Employee> retriedEmployee = employeeRepository.findById(employeeId);
        retriedEmployee.orElseThrow(() -> new EmployeeNotFoundException());

        return retriedEmployee.get();
    }

}
