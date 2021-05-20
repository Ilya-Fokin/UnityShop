package org.example.Service.User;

import org.example.Domains.User;

public interface UserService {
    String save(User user);
    User findByUsername(String username);
    Boolean checkUserByUsername(String username);
    boolean activateUser(String code);
    Boolean checkUserByEmail(String email);
}
