package ru.suyu.coffee_order_manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDto {
    private Long menuItemId;
    private String menuItemName;
    private int quantity;//количество
    private BigDecimal price;
    private BigDecimal subtotal;
}
