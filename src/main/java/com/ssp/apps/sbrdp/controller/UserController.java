package com.ssp.apps.sbrdp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String createUser() {
        return "Create user was called";
    }

    @PutMapping
    public String updateUser() {
        return "Update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete user was called";
    }

    @GetMapping
    public String getUsers() {
        return "Get All users was called";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        return "Get user By Id was called : " + id;
    }


    @GetMapping("/pagigation")
    public String getUsers(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(required = false) Integer limit, @RequestParam String order) {
        return String.format("Get All users called with pagination: page: %s, limit: %s, order: %s",
                page, limit, order);
    }
}
