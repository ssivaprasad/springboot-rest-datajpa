package com.ssp.apps.sbrdp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_dtl")
public class Employee {

    @Id
    private String id;

    private String name;
    private String email;

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
