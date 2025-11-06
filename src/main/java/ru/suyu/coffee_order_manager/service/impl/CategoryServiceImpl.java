package ru.suyu.coffee_order_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyu.coffee_order_manager.domain.Category;
import ru.suyu.coffee_order_manager.dto.CategoryDto;
import ru.suyu.coffee_order_manager.repository.CategoryRepository;
import ru.suyu.coffee_order_manager.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        Category save = categoryRepository.save(category);
        return toDto(save);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto (Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
