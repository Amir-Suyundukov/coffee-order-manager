package ru.suyu.coffee_order_manager.service;

import ru.suyu.coffee_order_manager.domain.MenuItem;
import ru.suyu.coffee_order_manager.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    List<MenuItemDto> findAll();
    MenuItemDto findById(Long id);
    List<MenuItemDto> findByCategoryName(String categoryName);
    MenuItemDto create(MenuItemDto menuItemDto);
    MenuItemDto update(Long id, MenuItemDto menuItemDto);
    void delete(Long id);
}
