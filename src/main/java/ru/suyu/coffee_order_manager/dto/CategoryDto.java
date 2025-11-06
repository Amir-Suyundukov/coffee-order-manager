package ru.suyu.coffee_order_manager.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private List<MenuItemDto> items;
}
