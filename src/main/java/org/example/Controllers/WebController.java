package org.example.Controllers;

import org.example.Domains.User;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/start_session")
    public String signInPage() {
        return "start_session";
    }

    @GetMapping("/account")
    public String accountPage() {
        return "account";
    }

    @GetMapping("/seller_account")
    public String sellerAccountPage() {
        return "seller_account";
    }

    @GetMapping("/basket")
    public String basketPage() {
        return "basket";
    }

    @GetMapping("/sign_up")
    public String signUpPage() {
        return "sign_up_form";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code) {
        User user = new User();

        if (userService.activateUser(code)) {
            return "success_activation";
        } else
            return "error_activation";
    }
}
