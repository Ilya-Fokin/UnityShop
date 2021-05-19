package org.example.Service.User;

import org.example.Domains.Role;
import org.example.Domains.User;
import org.example.Repository.RoleRepo;
import org.example.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public String save(User user) {
        Set<Role> roles = new HashSet<>();
        Set<User> users = new HashSet<>();

        Role role = roleRepo.findByName("buyer");

        if (checkUserByUsername(user.getUsername())) {
            return "Пользователь с таким логином уже существует";
        } else
            user.setActivationCode(UUID.randomUUID().toString());

        if(user.getEmail() != null) {

        }
            roles.add(role);
            users.add(user);

            role.setUsers(users);
            user.setRoles(roles);
            userRepo.save(user);
        return "Вы зарегистрированы";
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Boolean checkUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return true;
        } else return false;
    }
}
