package org.example.Repository;

import org.aspectj.weaver.ast.Or;
import org.example.Domains.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long> {
    Order findAllByUserIdAndBasketId(Long userid, Long basketId);
    List<Order> findAllByBasketId(Long basketId);

}
