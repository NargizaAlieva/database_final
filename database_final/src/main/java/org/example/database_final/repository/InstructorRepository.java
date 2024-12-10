package org.example.database_final.repository;

import org.example.database_final.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Instructor` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    /**
     * Checks if an instructor ID exists in the database.
     *
     * @param id The ID of the instructor to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Instructor i WHERE i.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Checks if an instructor email exists in the database.
     *
     * @param email The email of the instructor to check.
     * @return `true` if the email exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Instructor i WHERE LOWER(i.email) = LOWER(?1)")
    boolean isEmailExist(String email);

    /**
     * Retrieves an instructor by its ID.
     *
     * @param id The ID of the instructor.
     * @return An `Optional` containing the found instructor, or empty if not found.
     */
    @Query("SELECT i FROM Instructor i WHERE i.id = ?1")
    Optional<Instructor> findById(Long id);

    /**
     * Finds an instructor by its exact name.
     *
     * @param name The name of the instructor.
     * @return An `Optional` containing the found instructor, or empty if not found.
     */
    @Query("SELECT i FROM Instructor i WHERE LOWER(i.name) = LOWER(?1)")
    Optional<Instructor> findByName(String name);

    /**
     * Finds instructors whose names contain the specified string.
     *
     * @param name The name fragment to search for within instructor names.
     * @return A list of instructors with names containing the specified string.
     */
    @Query("SELECT i FROM Instructor i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Instructor> findByNameContains(String name);
}
