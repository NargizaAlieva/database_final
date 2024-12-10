package org.example.database_final.controller;

import org.example.dp_project.dto.CategoryDto;
import org.example.dp_project.dto.Response;
import org.example.dp_project.service.CategoryService;
import org.example.dp_project.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping( "/get-category-by-name/{categoryName}")
    public ResponseEntity<Response> getCategoryByName(@PathVariable String categoryName) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Category.", categoryService.getCategoryByName(categoryName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Category. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllCategory() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Categories.", categoryService.getAllCategories()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-category")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDto request) {
        try {
            CategoryDto categoryDto = categoryService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Category.", categoryDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Category is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-category")
    public ResponseEntity<Response> updateCategory(@RequestBody CategoryDto request) {
        try {
            CategoryDto createCategory = categoryService.updateCategory(request);
            return ResponseEntity.ok(new Response("Updated Category successfully.", createCategory));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Category is not updated. " + exception.getMessage(), null));
        }
    }
}
