package com.ssp.apps.sbrdp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.apps.sbrdp.dao.UserDao;
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.exception.DuplicateUserException;
import com.ssp.apps.sbrdp.exception.UserNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        Optional<User> retriedUser = userDao.getUser(user.getName());
        retriedUser.ifPresent((tempUser) -> {
            throw new DuplicateUserException();
        });
        return userDao.createUser(user);
    }

    public boolean updateUser(User user) {
        Optional<User> retriedUser = userDao.getUser(user.getUserId());
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        return userDao.updateUser(user);
    }


    public boolean deleteUser(int userId) {
        Optional<User> retriedUser = userDao.getUser(userId);
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        return userDao.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(int userId) {
        Optional<User> retriedUser = userDao.getUser(userId);
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        return retriedUser.get();
    }

}
