package ru.suyu.coffee_order_manager.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.suyu.coffee_order_manager.domain.Order;
import ru.suyu.coffee_order_manager.domain.OrderItem;
import ru.suyu.coffee_order_manager.dto.OrderDto;
import ru.suyu.coffee_order_manager.dto.OrderItemDto;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    // ВАЖНО-БУМАЖНО: OrderMapper зависит от MenuItemMapper, чтобы правильно преобразовать
    // информацию о блюдах внутри заказа. Но мы не будем его напрямую инжектить,
    // а сделаем проще - создадим методы-помощники.

    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        String waiterUsername = order.getWaiter() != null ? order.getWaiter().getUsername() : null;
        String customerUsername = order.getCustomer() != null ? order.getCustomer().getUsername() : null;

        return OrderDto.builder()
                .id(order.getId())
                .orderDateTime(order.getOrderDateTime())
                .status(order.getOrderStatus())
                .totalAmount(order.getTotalAmount())
                .waiterUsername(waiterUsername)
                .customerUsername(customerUsername)
                .items(order.getItems() != null ? order.getItems().stream()
                        .map(this::orderItemToDto)// используем внутренний метод-помощник
                        .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    // OrderItem -> OrderItemDto
    private OrderItemDto orderItemToDto(OrderItem orderItem){
        if (orderItem == null){
            return null;
        }

        return OrderItemDto.builder()
                .menuItemId(orderItem.getMenuItem().getId())
                .menuItemName(orderItem.getMenuItem().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPriceAtOrder())
                .subtotal(orderItem.getSubtotal())
                .build();
    }
}
