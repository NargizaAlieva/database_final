package org.example.database_final.repository;

import org.example.dp_project.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Instructor i WHERE i.id = ?1")
    boolean isIdExist(Long id);
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Instructor i WHERE i.email = ?1")
    boolean isEmailExist(String email);
    @Query("SELECT i FROM Instructor i WHERE i.id = ?1")
    Optional<Instructor> findById(Long id);
    @Query("SELECT i FROM Instructor i WHERE i.name = ?1")
    Optional<Instructor> findByName(String name);
    @Query("SELECT i FROM Instructor i WHERE i.name LIKE %?1%")
    List<Instructor> findByNameContains(String title);
}
