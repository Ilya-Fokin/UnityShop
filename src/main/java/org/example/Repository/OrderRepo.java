package org.example.Repository;

import org.example.Domains.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
    Order findAllByUserIdAndBasketId(Long userid, Long basketId);

}
