package org.example.Repository;

import org.example.Domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByActivationCode(String code);
}
