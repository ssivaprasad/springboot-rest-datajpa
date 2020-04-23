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
@Table(name = "user_dtl")
public class User {

    @Id
    private String userId;

    private String name;
    private String email;

    public User(String userId) {
        this.userId = userId;
    }
}
