package com.ssp.apps.sbrdp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssp.apps.sbrdp.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return user;
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
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {

        if (id < 30) {
            return new ResponseEntity<User>(new User(id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(new User(1, "Richardson", "I_Wont_Tell_You@fasak.com"),
                HttpStatus.OK);
    }


    @GetMapping("/pagigation")
    public String getUsers(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(name = "order", defaultValue = "ASC", required = false) String order) {
        return String.format("Get All users called with pagination: page: %s, limit: %s, order: %s",
                page, limit, order);
    }
}
