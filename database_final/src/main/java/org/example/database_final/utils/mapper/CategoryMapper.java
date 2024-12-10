package org.example.database_final.utils.mapper;

import org.example.database_final.dto.CategoryDto;
import org.example.database_final.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Category` entities and `CategoryDto` data transfer objects (DTOs).
 * This class is responsible for transforming the data between the domain model and the presentation layer.
 * It abstracts the conversion logic, reducing coupling between layers of the application.
 */
@Component
public class CategoryMapper {

    /**
     * Converts a `Category` entity to a `CategoryDto`.
     *
     * @param category The `Category` entity to be converted.
     * @return The corresponding `CategoryDto`.
     */
    public CategoryDto entityToDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    /**
     * Converts a list of `Category` entities to a list of `CategoryDto` objects.
     *
     * @param categories The list of `Category` entities.
     * @return A list of `CategoryDto` objects.
     */
    public List<CategoryDto> entityToDtoList(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a `CategoryDto` to a `Category` entity.
     *
     * @param categoryDto The `CategoryDto` to be converted.
     * @return The corresponding `Category` entity.
     */
    public Category dtoToEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
