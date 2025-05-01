package com.example.springsession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @GetMapping("/students")
    public String getStudent() {
        System.out.println("GET/student");
        return "student";
    }
}
