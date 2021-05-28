package org.example.Service.BasketService;

import org.example.Domains.Basket;
import org.example.Domains.Size;
import org.example.Repository.BasketRepo;
import org.example.Repository.ProductRepo;
import org.example.Repository.SizeRepo;
import org.example.Repository.UserRepo;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.SizeService.SizeService;
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
    private SizeRepo sizeRepo;

    @Autowired
    private ProductSizeService productSizeService;

    @Override
    public String addProductInBasket(Long userId, Long productId, Long sizeId) {
        Basket basket = new Basket();

        if (userService.findById(userId)) {
            if (productService.findById(productId)) {
                if (sizeRepo.findById(sizeId).isPresent()) {
                    Size size = sizeRepo.findById(sizeId).get();

                    if (checkBasket(userId, productId, size.getName())) {
                        basket = basketRepo.findByUserIdAndProductIdAndSize(userId, productId, size.getName());
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
                    basket.setSize(size.getName());

                    if (productSizeService.reduceProductCount(productId, sizeId)) {
                        basketRepo.save(basket);
                        return "Добавлено в Вашу корзину";
                    } else return "Размер распродан";

                } else return "Размер не найден";
            } else
                return "Продукт не найден";
        } else return "Пользователь не найден";
    }

    @Override
    public Boolean checkBasket(Long userId, Long productId, String size) {
        Basket basket = basketRepo.findByUserIdAndProductIdAndSize(userId, productId, size);

        if (basket == null) {
            return false;
        } else return true;
    }

    @Override
    public List<Basket> getAllByUserId(Long id) {
        return basketRepo.findAllByUserId(id);
    }

    @Override
    public String deleteProductInBasket(Long userId, Long productId, Long sizeId) {
        Size size = sizeRepo.findById(sizeId).get();
        Basket basket = basketRepo.findByUserIdAndProductIdAndSize(userId, productId, size.getName());
        int count = 0;
        if (basket != null) {
            count = basket.getCount();
            if (count == 1) {
                basketRepo.delete(basket);
                productSizeService.addCountProductSize(productId, sizeId);
                return "Товар удален из корзины";
            } else
               count = basket.getCount();
               count = count - 1;
               basket.setCount(count);
               basketRepo.save(basket);
               return "Товар удален из корзины в одном экземпляре";

        } else return "Данного товара нет в вашей корзине";
    }
}
