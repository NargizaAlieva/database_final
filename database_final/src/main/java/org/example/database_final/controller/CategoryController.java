package org.example.database_final.controller;

import org.example.database_final.dto.CategoryDto;
import org.example.database_final.dto.Response;
import org.example.database_final.service.CategoryService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing categories.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Constructor for CategoryController.
     *
     * @param categoryService the service for handling category operations.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves a category by its name.
     *
     * @param categoryName the name of the category to retrieve.
     * @return a ResponseEntity containing a Response object with the category data or an error message.
     */
    @GetMapping("/get-category-by-name/{categoryName}")
    public ResponseEntity<Response> getCategoryByName(@PathVariable String categoryName) {
        try {
            CategoryDto categoryDto = categoryService.getCategoryByName(categoryName);
            return ResponseEntity.ok(new Response("Successfully retrieved Category.", categoryDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Category. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all categories.
     *
     * @return a ResponseEntity containing a Response object with a list of all categories or an error message.
     */
    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllCategory() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved all Categories.", categoryService.getAllCategories()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Categories. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new category.
     *
     * @param request the DTO containing the new category data.
     * @return a ResponseEntity containing a Response object with the created category data or an error message.
     */
    @PostMapping(value = "/create-category")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDto request) {
        try {
            CategoryDto categoryDto = categoryService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Category.", categoryDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Category could not be saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Updates an existing category.
     *
     * @param request the DTO containing the updated category data.
     * @return a ResponseEntity containing a Response object with the updated category data or an error message.
     */
    @PutMapping(value = "/update-category")
    public ResponseEntity<Response> updateCategory(@RequestBody CategoryDto request) {
        try {
            CategoryDto updatedCategory = categoryService.updateCategory(request);
            return ResponseEntity.ok(new Response("Updated Category successfully.", updatedCategory));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Category could not be updated. " + exception.getMessage(), null));
        }
    }
}
