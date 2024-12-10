package org.example.database_final.service;

import org.example.database_final.dto.CategoryDto;
import org.example.database_final.entity.Category;

import java.util.List;

/**
 * Service interface for managing `Category` entities.
 * This service provides methods for handling category data including CRUD operations.
 */
public interface CategoryService {

    /**
     * Retrieves a `Category` by its unique ID.
     *
     * @param id The unique identifier of the category.
     * @return The `Category` entity corresponding to the provided ID.
     */
    Category getCategoryById(Long id);

    /**
     * Retrieves a `Category` entity by its name.
     *
     * @param name The name of the category.
     * @return The `Category` entity corresponding to the provided name.
     */
    Category getCategoryEntityByName(String name);

    /**
     * Retrieves a `CategoryDto` object by its name.
     *
     * @param name The name of the category.
     * @return A `CategoryDto` object corresponding to the provided category name.
     */
    CategoryDto getCategoryByName(String name);

    /**
     * Retrieves all categories available in the system.
     *
     * @return A list of `CategoryDto` objects representing all categories.
     */
    List<CategoryDto> getAllCategories();

    /**
     * Creates a new category.
     *
     * @param categoryDto The data transfer object containing the details of the category to be created.
     * @return The created `CategoryDto` object.
     */
    CategoryDto createCategory(CategoryDto categoryDto);

    /**
     * Updates an existing category.
     *
     * @param categoryDto The data transfer object containing the updated details of the category.
     * @return The updated `CategoryDto` object.
     */
    CategoryDto updateCategory(CategoryDto categoryDto);

    /**
     * Sorts categories by name.
     *
     * @return a list of category DTO responses sorted by name.
     */
    List<CategoryDto> sortByName();
}
