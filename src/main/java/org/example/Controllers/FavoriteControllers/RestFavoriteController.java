package org.example.Controllers.FavoriteControllers;

import org.example.Domains.Favorites;
import org.example.Domains.Product;
import org.example.Domains.User;
import org.example.Service.FavoritesService.FavoritesService;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        if (user == null) {
            return "Необходима авторизация";
        } else

        return favoritesService.addFavorite(user.getId(), productId);
    }

    @GetMapping("/get_all_favorite")
    public List<Product> getAllFavorites() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Product> products = new ArrayList<>();
        List<Favorites> favorites = favoritesService.getAllFavoritesByUserId(user.getId());
        if (favorites != null) {
            for (Favorites favorites1 : favorites) {
                products.add(productService.getProductById(favorites1.getProductId()));
            }
            return products;
        } else return null;
    }
}
