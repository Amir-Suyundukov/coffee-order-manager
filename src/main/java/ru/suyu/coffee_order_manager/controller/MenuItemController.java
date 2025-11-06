package ru.suyu.coffee_order_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.suyu.coffee_order_manager.domain.MenuItem;
import ru.suyu.coffee_order_manager.dto.MenuItemDto;
import ru.suyu.coffee_order_manager.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems(){
        List<MenuItemDto> menuItemDtos = menuItemService.findAll();
        return ResponseEntity.ok(menuItemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable Long id){
        MenuItemDto menuItemDto = menuItemService.findById(id);
        return ResponseEntity.ok(menuItemDto);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<MenuItemDto>> getMenuItemByCategory(@PathVariable String categoryName){
        List<MenuItemDto> menuItemDtos = menuItemService.findByCategoryName(categoryName);
        return ResponseEntity.ok(menuItemDtos);
    }

    //------------------------ методы для АДМИНА -----------------------------

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto menuItemDto){
        MenuItemDto create = menuItemService.create(menuItemDto);
        return new ResponseEntity<>(create, HttpStatus.CREATED);//201 created
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItemDto> updateMenuItems(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto){
        MenuItemDto update = menuItemService.update(id , menuItemDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMenuItems(@PathVariable Long id){
        menuItemService.delete(id);
        return ResponseEntity.noContent().build(); //204 No Content
    }

}
