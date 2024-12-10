package org.example.database_final.repository;

import org.example.database_final.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Category` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Checks if a category ID exists in the database.
     *
     * @param id The ID of the category to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Checks if a category name exists in the database.
     *
     * @param name The name of the category to check.
     * @return `true` if the name exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE LOWER(c.name) = LOWER(?1)")
    boolean isNameExist(String name);

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category.
     * @return An `Optional` containing the found category, or empty if not found.
     */
    @Query("SELECT c FROM Category c WHERE c.id = ?1")
    Optional<Category> findById(Long id);

    /**
     * Retrieves a category by its name.
     *
     * @param name The name of the category.
     * @return An `Optional` containing the found category, or empty if not found.
     */
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(?1)")
    Optional<Category> findByName(String name);
}
