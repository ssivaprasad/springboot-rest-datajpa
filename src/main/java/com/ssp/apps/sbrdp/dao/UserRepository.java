package com.ssp.apps.sbrdp.dao;

import org.springframework.data.repository.CrudRepository;
import com.ssp.apps.sbrdp.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
