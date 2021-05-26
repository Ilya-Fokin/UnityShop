package org.example.Service.BasketService;

import org.example.Domains.Basket;

import java.util.List;

public interface BasketService {
    String addProductInBasket(Long userIdLong, Long productId, Long sizeId);
    Boolean checkBasket(Long userId, Long productId);
    List<Basket> getAllByUserId(Long id);
}
