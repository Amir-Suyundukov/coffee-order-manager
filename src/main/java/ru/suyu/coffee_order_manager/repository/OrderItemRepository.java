package ru.suyu.coffee_order_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyu.coffee_order_manager.domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
}
