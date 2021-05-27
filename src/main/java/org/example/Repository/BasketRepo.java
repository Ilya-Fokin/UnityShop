package org.example.Repository;

import org.example.Domains.Basket;
import org.example.Domains.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketRepo extends CrudRepository<Basket, Long> {
    List<Basket> findByUserId(Long id);
    Basket findByUserIdAndProductIdAndSize(Long userId, Long productId, String size);
    List<Basket> findAllByUserId(Long id);
}
