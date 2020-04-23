package com.ssp.apps.sbrdp.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ssp.apps.sbrdp.dto.User;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByName(String name);

}
