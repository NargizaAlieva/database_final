package org.example.database_final.utils.mapper;

import org.example.dp_project.dto.CategoryDto;
import org.example.dp_project.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public List<CategoryDto> entityToDtoList(List<Category> categories) {
        return categories.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Category dtoToEntity (CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
