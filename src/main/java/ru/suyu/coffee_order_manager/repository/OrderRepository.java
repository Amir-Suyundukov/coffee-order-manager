package ru.suyu.coffee_order_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyu.coffee_order_manager.domain.Order;
import ru.suyu.coffee_order_manager.domain.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByWaiter_Username(String username);

    List<Order> findByCustomer_Username(String username);

}
