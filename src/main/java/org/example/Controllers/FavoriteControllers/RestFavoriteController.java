package org.example.Controllers.FavoriteControllers;

import org.example.Domains.Favorites;
import org.example.Domains.User;
import org.example.Service.FavoritesService.FavoritesService;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFavoriteController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/add_product_in_favorite/{productId}")
    public String addProductIntoFavorite(@PathVariable(name = "productId") Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        Favorites favorite = new Favorites();
        if (user == null) {
            return "Необходима авторизация";
        } else
            favorite.setUserId(user.getId());
        favorite.setProductId(productId);

        if (favoritesService.addFavorite(user.getId(), productId)) {
            return "Товар добавлен в избранное";
        } else return "Не удалось добавить товар в избранное";
    }
}
