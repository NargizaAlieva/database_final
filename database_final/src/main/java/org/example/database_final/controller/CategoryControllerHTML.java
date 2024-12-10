package org.example.database_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class CategoryControllerHTML {

    @GetMapping("/category")
    public String categories() {
        return "category.html";
    }

    @GetMapping("/course")
    public String courses() {
        return "course.html";
    }

    @GetMapping("/instructor")
    public String instructors() {
        return "instructor.html";
    }

    @GetMapping("/student")
    public String students() {
        return "student.html";
    }
    @GetMapping("/feedback")
    public String feedbacks() {
        return "feedback.html";
    }
}
