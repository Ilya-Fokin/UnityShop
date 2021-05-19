package org.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
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

}
