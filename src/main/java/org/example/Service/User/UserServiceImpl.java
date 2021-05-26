package org.example.Service.User;

import org.example.Domains.Role;
import org.example.Domains.User;
import org.example.Mail.MailSender;
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

    @Autowired
    private MailSender mailSender;

    @Override
    public String save(User user) {
        Set<Role> roles = new HashSet<>();
        Set<User> users = new HashSet<>();

        Role role = roleRepo.findByName("buyer");

        if (checkUserByUsername(user.getUsername())) {
            return "Пользователь с таким логином уже существует";
        } else

        /*if (checkUserByEmail(user.getEmail())) {
            return "Пользователь с такой почтой уже зарегистрирован";
        } else*/

        user.setActivationCode(UUID.randomUUID().toString());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        roles.add(role);
        users.add(user);

        role.setUsers(users);
        user.setRoles(roles);

        userRepo.save(user);

        if(user.getEmail() != null) {
            String message = String.format("Пожалуйста, для активации аккаунта перейдите по ссылке: http://localhost:8080/activate/%s",
                    user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation Code", message);
        } else return "Отсутствует адрес получателя";

        return "Письмо с подтверждением отправлено на почту";
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);
        return true;
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

    @Override
    public Boolean checkUserByEmail(String email) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            return false;
        } else return true;
    }

    @Override
    public Boolean findByUsernameAndPassword(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepo.findByUsername(username);
        String passwordUs = user.getPassword();
        if (passwordUs.equals(bCryptPasswordEncoder.encode(password))) {
            return true;
        } else return false;
    }

    @Override
    public Boolean updateUserRole(String username) {
        Set<Role> roles = new HashSet<>();
        Set<User> users = new HashSet<>();

        Role role = roleRepo.findByName("seller");

        if (checkUserByUsername(username)) {
            User user = findByUsername(username);

            if (user.getActivationCode() == null) {
                roles.add(role);
                users.add(user);

                user.setRoles(roles);

                userRepo.save(user);

                return true;
            } else return false;
        } else return false;
    }

    @Override
    public Boolean findById(Long id) {
        if (userRepo.findById(id).isPresent()) {
            return true;
        } else return false;
    }
}
