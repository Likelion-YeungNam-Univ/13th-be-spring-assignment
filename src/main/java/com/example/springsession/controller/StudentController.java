package com.example.springsession.controller;

import com.example.springsession.model.Student;
import com.example.springsession.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        System.out.println("GET/students/");
        return studentRepository.readAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        System.out.println("GET/students/" + id);
        return studentRepository.readById(id);
    }

    @PostMapping("/post")
    public Long postStudent(@RequestBody Student student) {
        System.out.println("POST/students/" + student);
        return studentRepository.create(student);
    }

    @PutMapping("/{id}")
    public Long putStudent(@PathVariable Long id, @RequestBody Student student){
        System.out.println("PUT/students/" + id);
        return studentRepository.updateAll(id, student);
    }

    @PatchMapping("/{id}")
    public Long patchStudent(@PathVariable Long id, @RequestBody Student student){
        System.out.println("PATCH/students/" + id);
        return studentRepository.update(id, student);
    }

    @DeleteMapping("/{id}")
    public Long deleteStudentById(@PathVariable Long id) {
        System.out.println("DELETE/students/" + id);
        return studentRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllStudent() {
        System.out.println("DELETE/students/");
        studentRepository.deleteAllById();
    }
}
