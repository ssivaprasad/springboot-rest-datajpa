package com.ssp.apps.sbrdp.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ssp.apps.sbrdp.dto.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByName(String name);

}
