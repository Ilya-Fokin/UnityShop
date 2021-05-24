package org.example.Controllers.LoginRegistrationController;

import org.example.Service.User.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestLoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/check_user")
    public String checkUser(@RequestBody String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        if (userService.findByUsernameAndPassword(username, password)) {
            return "Пользователь найден";
        } else return "Неверно указаны логин или пароль";
    }

    @GetMapping("/success_login")
    public String successLogin() {
        return "/account";
    }

    @GetMapping("/login?error")
    public String errorLogin() {
        return "error";
    }
}
