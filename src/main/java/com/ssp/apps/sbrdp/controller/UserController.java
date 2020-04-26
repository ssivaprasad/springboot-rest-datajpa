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
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> users = userService.getAllUsers();
        Optional.ofNullable(users).orElseGet(() -> new ArrayList<User>());

        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUser(id);
    }


    @GetMapping("/pagination")
    public String getUsers(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(name = "order", defaultValue = "ASC", required = false) String order) {
        return String.format(
                "== >> Get All users called with pagination: page: %s, limit: %s, order: %s", page,
                limit, order);
    }
}
