package com.ssp.apps.sbrdp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.apps.sbrdp.dao.UserRepository;
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.exception.DuplicateUserException;
import com.ssp.apps.sbrdp.exception.UserNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> retriedUser = userRepository.findByName(user.getName());
        retriedUser.ifPresent((tempUser) -> {
            throw new DuplicateUserException();
        });

        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        Optional<User> retriedUser = userRepository.findById(user.getId());
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        userRepository.save(user);
    }


    public void deleteUser(String userId) {
        Optional<User> retriedUser = userRepository.findById(userId);
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(String userId) {
        Optional<User> retriedUser = userRepository.findById(userId);
        retriedUser.orElseThrow(() -> new UserNotFoundException());

        return retriedUser.get();
    }

}
