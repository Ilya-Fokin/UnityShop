package org.example.Controllers.FavoriteControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoriteController {

    @GetMapping("/favorite")
    public String favoritePage() {
        return "favorite_page";
    }
}
