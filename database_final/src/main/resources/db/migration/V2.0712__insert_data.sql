INSERT INTO Instructor (Name, Email, Bio) VALUES
    ('John Doe', 'john.doe@example.com', 'Experienced data scientist with 10+ years in the field.'),
    ('Jane Smith', 'jane.smith@example.com', 'Full-stack web developer and coding enthusiast.'),
    ('Michael Brown', 'michael.b@example.com', 'Expert in AI and robotics, PhD in Computer Science.'),
    ('Laura Wilson', 'laura.w@example.com', 'Creative designer with a passion for user experiences.'),
    ('Robert Davis', 'robert.d@example.com', 'Certified ethical hacker with 8+ years of experience.'),
    ('Emily Clark', 'emily.c@example.com', 'PMP-certified professional with a focus on agile methodologies.'),
    ('David Johnson', 'david.j@example.com', 'AWS and Azure expert with a knack for scalable solutions.'),
    ('Sophia Martinez', 'sophia.m@example.com', 'Mobile app developer specializing in Android and iOS.');

INSERT INTO Category (Name, Description) VALUES
    ('Programming', 'Courses related to software development and programming languages.'),
    ('Data Science', 'Courses about data analysis, visualization, and machine learning.'),
    ('UI/UX Design', 'Courses about designing user interfaces and enhancing user experiences.'),
    ('Cybersecurity', 'Courses on information security and ethical hacking.'),
    ('Project Management', 'Courses focused on managing projects and teams.'),
    ('Cloud Computing', 'Courses on cloud platforms like AWS, Azure, and GCP.'),
    ('Mobile Development', 'Courses on building Android and iOS applications.'),
    ('Artificial Intelligence', 'Courses on machine learning, AI, and robotics.');

INSERT INTO Course (Title, Description, Duration, Price, Instructor_ID, Category_ID) VALUES
    ('Python for Beginners', 'Learn Python programming from scratch.', 30, 99.99, 1, 1),
    ('Machine Learning Basics', 'An introduction to machine learning concepts.', 40, 149.99, 1, 2),
    ('Advanced Web Development', 'Deep dive into full-stack development.', 60, 199.99, 2, 1),
    ('Ethical Hacking 101', 'Basics of cybersecurity and ethical hacking.', 45, 129.99, 5, 4),
    ('Design Thinking', 'Learn the principles of design thinking.', 25, 49.99, 4, 3),
    ('Agile Project Management', 'Master agile project management methodologies.', 35, 79.99, 6, 5),
    ('Cloud Fundamentals', 'Introduction to AWS and Azure platforms.', 50, 119.99, 7, 6),
    ('AI for Beginners', 'Learn the basics of artificial intelligence.', 40, 139.99, 3, 8);

INSERT INTO Student (Name, Email, Date_Of_Birth) VALUES
    ('Alice Johnson', 'alice.j@example.com', '2002-05-15'),
    ('Bob Williams', 'bob.w@example.com', '1999-11-25'),
    ('Charlie Brown', 'charlie.b@example.com', '2003-02-10'),
    ('Diana Clark', 'diana.c@example.com', '2000-07-22'),
    ('Ethan Davis', 'ethan.d@example.com', '2001-09-18'),
    ('Fiona Martinez', 'fiona.m@example.com', '2000-12-03'),
    ('George Wilson', 'george.w@example.com', '1998-06-28'),
    ('Hannah Smith', 'hannah.s@example.com', '2002-01-14');

INSERT INTO Enrollment (Student_ID, Course_ID, Completion_Status) VALUES
    (1, 1, FALSE),
    (1, 2, TRUE),
    (2, 3, FALSE),
    (3, 4, TRUE),
    (4, 5, FALSE),
    (5, 6, FALSE),
    (6, 7, TRUE),
    (7, 8, FALSE);

INSERT INTO Feedback (Student_ID, Course_ID, Rating, Comment) VALUES
    (1, 1, 4.5, 'Great introduction to Python programming.'),
    (1, 2, 5.0, 'Very detailed and easy to follow.'),
    (2, 3, 3.8, 'Good course but could use more examples.'),
    (3, 4, 4.9, 'Fantastic and highly informative.'),
    (4, 5, 4.2, 'Well-structured with useful insights.'),
    (5, 6, 3.5, 'Average course, needs improvement.'),
    (6, 7, 5.0, 'Excellent content and practical examples.'),
    (7, 8, 4.7, 'Great start for AI beginners.');
