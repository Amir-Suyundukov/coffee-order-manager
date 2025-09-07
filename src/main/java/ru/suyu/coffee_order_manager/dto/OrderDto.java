package ru.suyu.coffee_order_manager.dto;

import lombok.Builder;
import lombok.Data;
import ru.suyu.coffee_order_manager.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String waiterUsername;
    private String customerUsername;// может быть null
    private List<OrderItemDto> items;// список позиций в заказе
}