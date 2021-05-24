package org.example.Controllers.LoginRegistrationController;

import org.example.Domains.Role;
import org.example.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/start_session")
    public String signInPageLogout() { return "start_session";
    }

    @GetMapping("/admin_account")
    public String adminPage() {
        return "admin_account";
    }
}
