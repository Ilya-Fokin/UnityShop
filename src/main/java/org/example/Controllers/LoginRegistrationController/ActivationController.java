package org.example.Controllers.LoginRegistrationController;

import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ActivationController {
    @Autowired
    private UserService userService;

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code) {
        if (userService.activateUser(code)) {
            return "redirect:/activate_success";
        } else
            return "redirect:/activate_error";
    }

    @GetMapping("/activate_success")
    public String successActive() {
        return "start_session";
    }

    @GetMapping("/activate_error")
    public String errorActive() {
        return "error_activation";
    }
}
