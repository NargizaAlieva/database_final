Database Schema Documentation
Tables and Relationships
Instructor Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each instructor.
Name (VARCHAR(100), UNIQUE, NOT NULL): Full name of the instructor.
Email (VARCHAR(100), UNIQUE, NOT NULL): Email address of the instructor.
Bio (TEXT): Brief biography or description about the instructor.
Registration_Date (DATE, DEFAULT CURRENT_DATE): Date when the instructor was registered.
Category Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each category.
Name (VARCHAR(100), UNIQUE, NOT NULL): Name of the category (e.g., Programming, Data Science, Cybersecurity).
Description (TEXT): Detailed description about the category (e.g., courses related to software development, data analysis, etc.).
Course Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each course.
Title (VARCHAR(200), NOT NULL): Title of the course (e.g., Python for Beginners, Machine Learning Basics).
Description (TEXT): Detailed description about the course.
Duration (INT, NOT NULL): Duration of the course in hours.
Price (INT, NOT NULL): Price of the course.
Instructor_ID (BIGINT, REFERENCES Instructor(ID), ON DELETE SET NULL): Foreign key reference to the Instructor table, allowing NULL if the instructor is deleted.
Category_ID (BIGINT, REFERENCES Category(ID), ON DELETE SET NULL): Foreign key reference to the Category table, allowing NULL if the category is deleted.
Total_Enrollments (INT, DEFAULT 0, CHECK (Total_Enrollments >= 0)): Number of students enrolled in the course.
Average_Rating (FLOAT, DEFAULT 0.0, CHECK (Average_Rating BETWEEN 0 AND 5)): Average rating for the course.
Creation_Date (DATE, DEFAULT CURRENT_DATE): Date when the course was created.
Student Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each student.
Name (VARCHAR(100), UNIQUE, NOT NULL): Full name of the student.
Email (VARCHAR(100), UNIQUE, NOT NULL): Email address of the student.
Date_Of_Birth (DATE, NOT NULL): Date of birth of the student.
Registration_Date (DATE, DEFAULT CURRENT_DATE): Date when the student was registered.
Enrollment Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each enrollment.
Student_ID (BIGINT, REFERENCES Student(ID), ON DELETE CASCADE): Foreign key reference to the Student table, cascade delete if the student is deleted.
Course_ID (BIGINT, REFERENCES Course(ID), ON DELETE CASCADE): Foreign key reference to the Course table, cascade delete if the course is deleted.
Enrollment_Date (DATE, DEFAULT CURRENT_DATE): Date when the student enrolled in the course.
Completion_Status (BOOLEAN, DEFAULT FALSE): Indicates whether the student has completed the course (true if completed, false otherwise).
Feedback Table

Attributes:
ID (BIGSERIAL, Primary Key): Unique identifier for each feedback entry.
Student_ID (BIGINT, REFERENCES Student(ID), ON DELETE CASCADE): Foreign key reference to the Student table, cascade delete if the student is deleted.
Course_ID (BIGINT, REFERENCES Course(ID), ON DELETE CASCADE): Foreign key reference to the Course table, cascade delete if the course is deleted.
Rating (FLOAT, NOT NULL, CHECK (Rating BETWEEN 0 AND 5)): Rating given by the student for the course.
Comment (TEXT): Optional comments or review provided by the student.
Feedback_Date (DATE, DEFAULT CURRENT_DATE): Date when the feedback was submitted.
Relationships
One-to-Many Relationship:

Instructor to Course: An instructor can teach multiple courses. The Instructor_ID in the Course table references the ID in the Instructor table. If an instructor is deleted, the Instructor_ID in all related courses will be set to NULL.
One-to-Many Relationship:

Category to Course: A course belongs to a specific category (e.g., Programming, Data Science). The Category_ID in the Course table references the ID in the Category table. If a category is deleted, the Category_ID in all related courses will be set to NULL.
Many-to-One Relationship:

Student to Enrollment: A student can be enrolled in multiple courses. The Student_ID in the Enrollment table references the ID in the Student table. If a student is deleted, all related enrollments will be deleted due to the cascade delete constraint.
Many-to-One Relationship:

Course to Enrollment: A course can have multiple students enrolled. The Course_ID in the Enrollment table references the ID in the Course table. If a course is deleted, all related enrollments will be deleted due to the cascade delete constraint.
Many-to-One Relationship:

Student to Feedback: A student can give feedback for multiple courses. The Student_ID in the Feedback table references the ID in the Student table. If a student is deleted, all related feedback entries will be deleted due to the cascade delete constraint.
Many-to-One Relationship:

Course to Feedback: A course can receive feedback from multiple students. The Course_ID in the Feedback table references the ID in the Course table. If a course is deleted, all related feedback entries will be deleted due to the cascade delete constraint.
Database Constraints
Primary Keys:

ID in Instructor, Category, Course, Student, Enrollment, and Feedback tables.
Foreign Key Constraints:

Instructor_ID in Course table references ID in Instructor table.
Category_ID in Course table references ID in Category table.
Student_ID in Enrollment table references ID in Student table (Cascade delete).
Course_ID in Enrollment table references ID in Course table (Cascade delete).
Student_ID in Feedback table references ID in Student table (Cascade delete).
Course_ID in Feedback table references ID in Course table (Cascade delete).
Unique Constraints:

Name in Instructor table.
Email in Instructor and Student tables.
Name in Category table.
Check Constraints:

Total_Enrollments in Course table must be non-negative.
Average_Rating in Course table must be between 0 and 5.
Rating in Feedback table must be between 0 and 5.
This documentation outlines the structure, relationships, and constraints of the database schema, ensuring data integrity and consistency across all tables.