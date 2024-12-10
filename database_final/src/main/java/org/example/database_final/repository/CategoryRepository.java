package org.example.database_final.repository;

import org.example.dp_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.id = ?1")
    boolean isIdExist(Long id);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.name = ?1")
    boolean isNameExist(String name);
    @Query("SELECT c FROM Category c WHERE c.id = ?1")
    Optional<Category> findById(Long id);
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(?1)")
    Optional<Category> findByName(String name);
}
