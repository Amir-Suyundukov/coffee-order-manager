package ru.suyu.coffee_order_manager.service;

import ru.suyu.coffee_order_manager.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto create(CategoryDto categoryDto);
    void delete(Long id);
}
