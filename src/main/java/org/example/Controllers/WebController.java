package org.example.Controllers;

import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String signInPage() { return "start_session";
    }

    @GetMapping("/account")
    public String accountPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("admin"));
        boolean hasSeller = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("seller"));
        if (hasAdmin) {
            return "redirect:/admin_account";
        } else
            if (hasSeller) {
                return "redirect:/seller_account";
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

    @GetMapping("/admin_account/add_seller_page")
    public String addSellerPage() {
        return "add_seller";
    }

    @GetMapping("/admin_account/warehouse_page")
    public String allListProduct() {
        return "warehouse_products";
    }


    @GetMapping("/Mens/{category}")
    public String mensProductByCategory(@PathVariable(name = "category") String category) {
        return "mens_product_page";
    }

    @GetMapping("/Woomens/{category}")
    public String woomensProductByCategory(@PathVariable(name = "category") String category) {
        return "mens_product_page";
    }

    @GetMapping("/search_product_page")
    public String searchPage() {
        return "search_product";
    }


    @GetMapping("/product_page/{id}")
    public String productPage(@PathVariable(name = "id") String response) {
        return "product_page";
    }

}
