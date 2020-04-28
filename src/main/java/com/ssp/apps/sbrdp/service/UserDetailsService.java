package com.ssp.apps.sbrdp.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ssp.apps.sbrdp.dao.UserRepository;
import com.ssp.apps.sbrdp.dto.UserPrincipal;
import com.ssp.apps.sbrdp.entity.User;
import com.ssp.apps.sbrdp.exception.UsernotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("== >> Trying to get the User Details: {}", username);
        Optional<User> user = userRepository.findById(username);
        user.orElseThrow(() -> new UsernotFoundException("No user found with id: " + username));
        return new UserPrincipal(user.get());
    }

}
