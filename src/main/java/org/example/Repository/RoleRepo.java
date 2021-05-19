package org.example.Repository;

import org.example.Domains.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
