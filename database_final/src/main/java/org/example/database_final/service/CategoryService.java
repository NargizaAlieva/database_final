package org.example.database_final.service;

import org.example.database_final.dto.CategoryDto;
import org.example.database_final.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    Category getCategoryEntityByName(String name);
    CategoryDto getCategoryByName(String name);
    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
}
