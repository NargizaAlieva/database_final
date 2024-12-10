package org.example.database_final.service.impl;

import org.example.database_final.dto.CategoryDto;
import org.example.database_final.dto.CourseDtoResponse;
import org.example.database_final.entity.Category;
import org.example.database_final.repository.CategoryRepository;
import org.example.database_final.service.CategoryService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing categories.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /**
     * Constructor for CategoryServiceImpl.
     *
     * @param categoryRepository the repository for accessing category data.
     * @param categoryMapper     the mapper for converting between Category entities and DTOs.
     */
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Checks if a category with the given ID exists.
     *
     * @param id the category ID.
     * @return false (no return value used).
     * @throws AlreadyExistException if the category ID already exists.
     */
    private boolean isIdExist(Long id) {
        if (categoryRepository.existsById(id)) {
            throw new AlreadyExistException("Category", "id");
        }
        return false;
    }

    /**
     * Checks if a category with the given name exists.
     *
     * @param name the category name.
     * @return false (no return value used).
     * @throws AlreadyExistException if the category name already exists.
     */
    private boolean isNameExist(String name) {
        if (categoryRepository.isNameExist(name)) {
            throw new AlreadyExistException("Category", "name");
        }
        return false;
    }

    /**
     * Saves a category entity to the database.
     *
     * @param category the category entity to save.
     * @return the saved category entity.
     */
    private Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Retrieves a category by ID.
     *
     * @param id the ID of the category.
     * @return the category entity.
     * @throws ObjectNotFoundException if the category with the given ID does not exist.
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category"));
    }

    /**
     * Retrieves a category entity by name.
     *
     * @param name the name of the category.
     * @return the category entity.
     * @throws ObjectNotFoundException if the category with the given name does not exist.
     */
    @Override
    public Category getCategoryEntityByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Category"));
    }

    /**
     * Retrieves a category DTO by name.
     *
     * @param name the name of the category.
     * @return the category DTO.
     * @throws ObjectNotFoundException if the category with the given name does not exist.
     */
    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.entityToDto(categoryRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Category")));
    }

    /**
     * Retrieves a list of all category DTOs.
     *
     * @return a list of category DTOs.
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.entityToDtoList(categoryRepository.findAll());
    }

    /**
     * Creates a new category.
     *
     * @param categoryDto the category DTO containing the new category's details.
     * @return the created category DTO.
     * @throws AlreadyExistException if a category with the same name already exists.
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (!isNameExist(categoryDto.getName())) {
            return categoryMapper.entityToDto(save(categoryMapper.dtoToEntity(categoryDto)));
        }
        return null;
    }

    /**
     * Updates an existing category.
     *
     * @param categoryDto the category DTO containing the updated details.
     * @return the updated category DTO.
     * @throws ObjectNotFoundException if the category with the given ID does not exist.
     * @throws AlreadyExistException  if the new name provided for the category already exists.
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category oldCategory = categoryMapper.dtoToEntity(categoryDto);
        Category newCategory = getCategoryById(categoryDto.getId());

        // Check if the name has been changed to avoid conflicts with existing names.
        if (!newCategory.getName().equals(oldCategory.getName())) {
            isNameExist(oldCategory.getName());
        }

        // Update category details if new values are provided.
        if (!(oldCategory.getName() == null)) {
            newCategory.setName(oldCategory.getName());
        }
        if (!(oldCategory.getDescription() == null)) {
            newCategory.setDescription(oldCategory.getDescription());
        }

        return categoryMapper.entityToDto(save(newCategory));
    }

    /**
     * Sorts categories by name.
     *
     * @return a list of category DTO responses sorted by name.
     */
    @Override
    public List<CategoryDto> sortByName() {
        return getAllCategories().stream()
                .sorted(Comparator.comparing(CategoryDto::getName)) // Sort by course name
                .collect(Collectors.toList());
    }
}
