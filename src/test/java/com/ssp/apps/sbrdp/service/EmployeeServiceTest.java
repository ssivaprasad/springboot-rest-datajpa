package com.ssp.apps.sbrdp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ssp.apps.sbrdp.dao.EmployeeRepository;
import com.ssp.apps.sbrdp.entity.Employee;
import com.ssp.apps.sbrdp.exception.DuplicateEmployeeException;
import com.ssp.apps.sbrdp.exception.EmployeeNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();



    @Test
    public void createEmployee() {
        when(employeeRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        Employee employee =
                employeeService.createEmployee(new Employee("123", "dummy", "dummy@test.com"));
        assertNotNull(employee);
    }

    @Test(expected = DuplicateEmployeeException.class)
    public void createEmployee_should_throw_DuplicateEmployeeException() {
        when(employeeRepository.findByName(any(String.class)))
                .thenReturn(Optional.of(new Employee()));
        employeeService.createEmployee(new Employee("133", "dummy", "dummy@test.com"));
    }

    @Test
    public void updateEmployee() {
        when(employeeRepository.findById(any(String.class)))
                .thenReturn(Optional.of(new Employee()));
        employeeService.updateEmployee(new Employee("133", "dummy", "dummy@test.com"));

        verify(employeeRepository).save(any(Employee.class));

    }

    @Test(expected = EmployeeNotFoundException.class)
    public void updateEmployee_should_throw_EmployeeNotFoundException() {
        when(employeeRepository.findById(any(String.class))).thenReturn(Optional.empty());
        employeeService.updateEmployee(new Employee("133", "dummy", "dummy@test.com"));
    }

    @Test
    public void deleteEmployee() {
        when(employeeRepository.findById(any(String.class)))
                .thenReturn(Optional.of(new Employee()));
        employeeService.deleteEmployee("123");

        verify(employeeRepository).deleteById(any(String.class));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void deleteEmployee_should_throw_EmployeeNotFoundException() {
        when(employeeRepository.findById(any(String.class))).thenReturn(Optional.empty());
        employeeService.deleteEmployee("21312");
    }

    @Test
    public void getEmployee() {
        when(employeeRepository.findById(any(String.class)))
                .thenReturn(Optional.of(new Employee()));
        Employee employee = employeeService.getEmployee("1212");
        assertNotNull(employee);
        assertThat(employee, is(notNullValue()));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployee_should_throw_EmployeeNotFoundException() {
        when(employeeRepository.findById(any(String.class))).thenReturn(Optional.empty());
        employeeService.getEmployee("1212");
    }

    @Test
    @Ignore
    public void checkExceptionMessage() {
        thrown.expect(EmployeeNotFoundException.class);
        thrown.expectMessage("Employee Not Exists in DB");

        when(employeeRepository.findById(any(String.class))).thenReturn(Optional.empty());
        employeeService.getEmployee("1212");
    }

}
