package ru.suyu.coffee_order_manager.dto;

import lombok.Data;
import ru.suyu.coffee_order_manager.repository.OrderItemRepository;

import java.util.List;

@Data
public class CreateOrderRequestDto {

    private String customerUsername;
    private List<OrderItemRequestDto> items;

    @Data
    public static class OrderItemRequestDto{
        private Long menuItemId;
        private int quantity;
    }
}
