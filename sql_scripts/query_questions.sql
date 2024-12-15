-- Retrieve all categories
SELECT * FROM Category;

-- Retrieve a category by its ID
SELECT * FROM Category c WHERE c.id = 1;

-- Retrieve a category by its name
SELECT * FROM Category c WHERE LOWER(c.name) = 'data science';

-- Finds all courses in the database
SELECT * FROM Course c;

-- Retrieves the total number of courses in the database
SELECT COUNT(c) FROM Course c;

-- Finds courses sorted by title in ascending order
SELECT * FROM Course c ORDER BY LOWER(c.title) ASC;

-- Finds courses with a price greater than a specific amount
SELECT * FROM Course c WHERE c.price > 100;

-- Finds courses with a rating greater than a specific value
SELECT * FROM Course c WHERE c.average_rating BETWEEN 3 AND 5;

-- Finds courses by instructor ID
SELECT * FROM Course c WHERE c.instructor_id = 1;

-- Finds courses by student ID from the enrollment table:
SELECT c.*
FROM Course c
         JOIN Enrollment e ON c.id = e.course_id
WHERE e.student_id = 1;

-- Finds the course with the most total enrollments
SELECT c.id, c.title, c.total_enrollments FROM Course c
WHERE c.total_enrollments = (
    SELECT MAX(total_enrollments)
    FROM Course
);

-- Retrieve feedback by student name
SELECT f.*
FROM Feedback f
    JOIN Student s ON f.student_id = s.id
    WHERE s.id = f.student_id;

-- Retrieve feedback by course name
SELECT f.*
FROM Feedback f
         JOIN Course c ON f.course_id = c.id
WHERE LOWER(c.title) LIKE 'ai for beginners';

-- Retrieve feedback with the highest rating
SELECT f.*
FROM Feedback f
WHERE f.rating = (SELECT MAX(rating) FROM Feedback);

-- Retrieve an instructor by its ID
SELECT * FROM Instructor i WHERE i.id = 1;

-- Finds instructors whose names contain the specified string
SELECT * FROM Instructor i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', 'john', '%'));

-- Finds student by name
SELECT * FROM Student s WHERE s.name = 'Alice Johnson';

-- Finds students with more enrollments
SELECT s.id, s.name, COUNT(e.student_id) AS total_enrollments
FROM Student s
         JOIN Enrollment e ON s.id = e.student_id
GROUP BY s.id, s.name
HAVING COUNT(e.student_id) > 1;

-- Finds students enrolled in a specific course
SELECT s.id, s.name, e.enrollment_date
FROM Student s
         JOIN Enrollment e ON s.id = e.student_id
         JOIN Course c ON e.course_id = c.id
WHERE LOWER(c.title) = LOWER('Machine Learning Basics');