package org.example.Controllers.BasketControllers;

import org.example.Domains.Basket;
import org.example.Domains.Product;
import org.example.Domains.ProductSize;
import org.example.Domains.User;
import org.example.Repository.ProductRepo;
import org.example.Service.BasketService.BasketService;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.SizeService.SizeService;
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
public class BasketController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/add_product_in_basket/{productId}/{size}")
    public String addProductInBasket(@PathVariable(name = "productId") Long productId, @PathVariable(name = "size") String size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        if (user == null) {
            return "Необходима авторизация";
        }

        Long sizeId = sizeService.getSizeId(size);

        if (sizeId != null) {
            return basketService.addProductInBasket(user.getId(), productId, sizeId);
        } else return "Выберете размер из списка";
    }

    @PostMapping("/delete_product_in_basket/{id}/{size}")
    public String deleteProductInBasket(@PathVariable(name = "id") Long productId, @PathVariable(name = "size") String size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        if (user == null) {
            return "Необходима авторизация";
        }

        Long sizeId = sizeService.getSizeId(size);

        if (sizeId != null) {
            return basketService.deleteProductInBasket(user.getId(), productId, sizeId);
        } else return "Выберете размер из списка";
    }

    @GetMapping("/get_all_basket")
    public List<Basket> getAllBasketById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Basket> baskets = new ArrayList<>();

        if (user != null) {
            Long id = user.getId();

            if (id != null) {
                baskets = basketService.getAllByUserId(id);
                if (baskets != null) {
                    return baskets;
                } else
                    return null;
            } else
                return null;
            } else
                return null;
    }
}
