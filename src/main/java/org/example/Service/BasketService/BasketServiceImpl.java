package org.example.Service.BasketService;

import org.example.Domains.Basket;
import org.example.Repository.BasketRepo;
import org.example.Repository.ProductRepo;
import org.example.Repository.UserRepo;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepo basketRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductSizeService productSizeService;

    @Override
    public String addProductInBasket(Long userId, Long productId, Long sizeId) {
        Basket basket = new Basket();

        if (userService.findById(userId)) {
            if (productService.findById(productId)) {

                if (checkBasket(userId, productId)) {
                    basket = basketRepo.findByUserIdAndProductId(userId, productId);
                    int count = basket.getCount();
                    int newCount = count + 1;
                    basket.setCount(newCount);

                    if (productSizeService.reduceProductCount(productId, sizeId)) {
                        basketRepo.save(basket);
                        return "Добавлено в Вашу корзину";
                    } else return "Не удалось удалить одну позицию на складе";
                } else

                basket.setProductId(productId);
                basket.setUserId(userId);
                basket.setCount(1);

                if (productSizeService.reduceProductCount(productId, sizeId)) {
                    basketRepo.save(basket);
                    return "Добавлено в Вашу корзину";
                } else return "Не удалось удалить одну позицию на складе";
            } else
                return "Продукт не найден";
        } else return "Пользователь не найден";
    }

    @Override
    public Boolean checkBasket(Long userId, Long productId) {
        Basket basket = basketRepo.findByUserIdAndProductId(userId, productId);

        if (basket == null) {
            return false;
        } else return true;
    }

    @Override
    public List<Basket> getAllByUserId(Long id) {
        return basketRepo.findAllByUserId(id);
    }
}
