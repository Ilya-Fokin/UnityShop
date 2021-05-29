package org.example.Controllers.OrderControllers;

import org.example.Domains.Basket;
import org.example.Domains.User;
import org.example.Service.BasketService.BasketService;
import org.example.Service.OrderService.OrderService;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.SizeService.SizeService;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
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
    private OrderService orderService;

    @PostMapping("/create_order")
    public String createOrder() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        if (user == null) {
            return "Необходима авторизация";
        } else {
            Long userId = user.getId();
            List<Basket> baskets = basketService.getAllByUserId(userId);
            if (baskets != null) {
                for (Basket basket : baskets) {
                   orderService.createOrder(userId, basket.getId());
                }
                return "Заказ оформлен";
            } else return "Ваша корзина пуста";
        }

    }
}
