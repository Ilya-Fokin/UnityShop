package org.example.Service.User;

import org.example.Domains.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
