package org.example.Controllers;

import org.example.Domains.User;
import org.example.Repository.UserRepo;
import org.example.Service.User.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebAjaxController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign_up_user")
    public String signUp(@RequestBody String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        String name = jsonObject.getString("name");
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        String date = jsonObject.getString("date");

        User user = new User(name, username, email, password, date);

        return userService.save(user);
    }

}
