package ru.suyu.coffee_order_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyu.coffee_order_manager.domain.Category;
import ru.suyu.coffee_order_manager.domain.MenuItem;
import ru.suyu.coffee_order_manager.dto.MenuItemDto;
import ru.suyu.coffee_order_manager.mapper.MenuItemMapper;
import ru.suyu.coffee_order_manager.repository.CategoryRepository;
import ru.suyu.coffee_order_manager.repository.MenuItemRepository;
import ru.suyu.coffee_order_manager.service.MenuItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> findAll() {
        return menuItemRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItemDto findById(Long id) {
        return menuItemRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("MenuItems not foud with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> findByCategoryName(String categoryName) {
        return menuItemRepository.findByCategory_Name(categoryName).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public MenuItemDto create(MenuItemDto menuItemDto) {
        Category category = categoryRepository.findByName(menuItemDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found with name: " + menuItemDto.getCategoryName()));

        MenuItem menuItem = mapper.toEntity(menuItemDto);
        menuItem.setCategory(category);
        MenuItem save = menuItemRepository.save(menuItem);

        return mapper.toDto(save);
    }

    @Override
    @Transactional
    public MenuItemDto update(Long id, MenuItemDto menuItemDto) {
        MenuItem existingMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItems not found with id :" + id));

        existingMenuItem.setName(menuItemDto.getName());
        existingMenuItem.setDescription(menuItemDto.getDescription());
        existingMenuItem.setPrice(menuItemDto.getPrice());
        existingMenuItem.setImageUrl(menuItemDto.getImageUrl());

        if (menuItemDto.getCategoryName() != null){
            Category category = categoryRepository.findByName(menuItemDto.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Category not found with name: " + menuItemDto.getCategoryName()));
            existingMenuItem.setCategory(category);
        }

        MenuItem update = menuItemRepository.save(existingMenuItem);
        return mapper.toDto(update);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!menuItemRepository.existsById(id)){
            throw new RuntimeException("MenuItem not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
    }
}
