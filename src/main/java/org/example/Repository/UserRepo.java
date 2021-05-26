package org.example.Repository;

import org.example.Domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByActivationCode(String code);
    Optional<User> findById(Long id);
}
