package org.example.Service.OrderService;

import org.example.Domains.Order;
import org.example.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Boolean createOrder(Long userId, Long basketId) {
        if (orderRepo.findAllByUserIdAndBasketId(userId, basketId) == null) {
            Order order = new Order();
            order.setBasketId(basketId);
            order.setUserId(userId);

            orderRepo.save(order);

            return true;
        } else return false;
    }
}
