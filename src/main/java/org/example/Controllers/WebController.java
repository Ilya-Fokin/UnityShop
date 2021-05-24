package org.example.Controllers;

import org.example.Domains.User;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/start_session")
    public String signInPage() { return "start_session";
    }

    @GetMapping("/account")
    public String accountPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("admin"));

        if (hasAdmin) {
            return "redirect:/admin_account";
        } else
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

    @GetMapping("/photo_album")
    public String photoAlbumPage() {
        return "photo_album";
    }



}
