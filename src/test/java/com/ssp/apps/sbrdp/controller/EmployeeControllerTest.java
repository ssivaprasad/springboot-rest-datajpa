package com.ssp.apps.sbrdp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssp.apps.sbrdp.entity.Employee;
import com.ssp.apps.sbrdp.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebSecurity.class)})
@AutoConfigureMockMvc(addFilters = false)
/*
 * @WebMvcTest(value = EmployeeController.class, excludeFilters = {@ComponentScan.Filter(type =
 * FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration.class)})
 * 
 * @AutoConfigureMockMvc(addFilters = false)
 */
/* @Import(SecurityTestConfiguration.class) */
public class EmployeeControllerTest {

    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("123", "Somarouthu", "dummy@Test.com"));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockmvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("Somarouthu"))
                .andExpect(jsonPath("$.[0].email").value("dummy@Test.com"));

    }

    @Test
    public void testGetAllEmployees_should_be_empty() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(null);

        mockmvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").doesNotExist());
    }


    @Test
    public void testSave() throws Exception {
        Employee employeeResponse = new Employee("xxxxx", "Siva", "lsdhf");
        Employee employeeRequest = new Employee(null, "Somarouthu", "lsdhf");

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employeeResponse);

        MockHttpServletRequestBuilder request =
                post("/employees").contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonValue(employeeRequest)).accept(APPLICATION_JSON_VALUE);

        mockmvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Siva"));
    }


    @Test
    public void deleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(any(String.class));
        mockmvc.perform(delete("/employees/123").contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void updateEmployee() throws Exception {
        doNothing().when(employeeService).updateEmployee(any(Employee.class));
        mockmvc.perform(put("/employees/123").contentType(APPLICATION_JSON_VALUE)
                .content(asJsonValue(new Employee("xxxxx", "Siva", "lsdhf")))
                .accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    private String asJsonValue(Employee employee) throws JsonProcessingException {
        return objectMapper.writeValueAsString(employee);
    }

}


