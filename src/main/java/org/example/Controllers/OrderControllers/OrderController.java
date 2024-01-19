package org.example.Controllers.OrderControllers;

import org.example.Domains.Basket;
import org.example.Domains.Product;
import org.example.Domains.User;
import org.example.Repository.BasketRepo;
import org.example.Repository.ProductRepo;
import org.example.Repository.UserRepo;
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
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private BasketRepo basketRepo;

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
        int sum = 0;

        if (user == null) {
            return "Необходима авторизация";
        } else {
            Long userId = user.getId();
            List<Basket> baskets = basketService.getAllByUserId(userId);
            if (baskets != null) {
                for (Basket basket : baskets) {
                   orderService.createOrder(userId, basket.getId());
                   Product product = productRepo.findById(basket.getProductId()).get();
                   int price = product.getPrice();
                   sum = sum + price;
                }
                user.setMoney(user.getMoney() - sum);
                userRepo.save(user);

                return "Заказ оформлен";
            } else return "Ваша корзина пуста";
        }

    }
}
