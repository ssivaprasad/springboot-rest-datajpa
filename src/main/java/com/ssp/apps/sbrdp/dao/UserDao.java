package com.ssp.apps.sbrdp.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.ssp.apps.sbrdp.dto.User;

@Repository
public class UserDao {

    private Map<Integer, User> users = new HashMap<>();
    int count = 0;

    public User createUser(User user) {
        Integer generateUserId = generateUserId();

        users.put(generateUserId, new User(generateUserId, user.getName(), user.getEmail()));

        return user;
    }

    public Optional<User> getUser(int userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public boolean updateUser(User user) {
        Optional<User> userOptional = Optional.ofNullable(users.get(user.getUserId()));
        userOptional.ifPresent((tempUser) -> users.put(user.getUserId(),
                new User(user.getUserId(), user.getName(), user.getEmail())));

        return userOptional.isPresent() ? true : false;
    }

    public boolean deleteUser(int userId) {
        Optional<User> userOptional = Optional.ofNullable(users.get(userId));
        userOptional.ifPresent(tempuser -> users.remove(tempuser.getUserId()));

        return userOptional.isPresent() ? true : false;
    }

    public Optional<User> getUser(String userName) {
        return users.values().stream().filter(user -> user.getName().equals(userName)).findFirst();
    }

    private Integer generateUserId() {
        return ++count;
    }
}
