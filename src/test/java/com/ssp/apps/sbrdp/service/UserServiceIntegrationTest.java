package com.ssp.apps.sbrdp.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ssp.apps.sbrdp.SpringBootRestDataJpaApplication;
import com.ssp.apps.sbrdp.dto.User;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestDataJpaApplication.class)
@Slf4j
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAllUsers() {
        List<User> users = userService.getAllUsers();
        users.stream().forEach(user -> log.info("== >> USer Details: {}", user));
    }

}
