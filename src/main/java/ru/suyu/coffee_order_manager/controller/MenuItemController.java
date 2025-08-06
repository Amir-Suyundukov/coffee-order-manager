package ru.suyu.coffee_order_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import ru.suyu.coffee_order_manager.dto.MenuItemDto;
import ru.suyu.coffee_order_manager.service.MenuItemService;

import java.util.List;

@Controller
@RestController("/api/v1/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    public ResponseEntity<List<MenuItemDto>> getAllMenuItems(){
        List<MenuItemDto> menuItemDtos = menuItemService.findAll();
        return ResponseEntity.ok(menuItemDtos);
    }
}
