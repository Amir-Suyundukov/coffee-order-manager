package ru.suyu.coffee_order_manager.mapper;

import org.springframework.stereotype.Component;
import ru.suyu.coffee_order_manager.domain.MenuItem;
import ru.suyu.coffee_order_manager.dto.MenuItemDto;

@Component
public class MenuItemMapper {

    public MenuItemDto toDto(MenuItem menuItem){
        if(menuItem == null){
            return null;
        }
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .imageUrl(menuItem.getImageUrl())
                .categoryName(menuItem.getCategory() != null ? menuItem.getCategory().getName() : null)
                .build();
    }

    public MenuItem toEntity(MenuItemDto dto){
        if (dto == null){
            return null;
        }
        return MenuItem.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}
