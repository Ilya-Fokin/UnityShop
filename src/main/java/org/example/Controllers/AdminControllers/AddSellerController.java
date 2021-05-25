package org.example.Controllers.AdminControllers;

import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddSellerController {
    @Autowired
    private UserService userService;

    @PostMapping("/add_seller/{username}")
    public String addSeller(@PathVariable String username) {
        if (userService.updateUserRole(username)) {
            return "Продавец добавлен";
        } else return "Пользователь с таким логином не существует";
    }
}
