package com.carManagement.dataaccessobject;

import com.carManagement.domainobject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
