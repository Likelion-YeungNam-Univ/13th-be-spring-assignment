package com.example.springsession.repository;

import com.example.springsession.model.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<Long, Student> studentDB = new HashMap<>();

    public Long create(Student student) {
        studentDB.put(student.getId(), student);
        return student.getId();
    }

    public Student readById(Long id) {
        return studentDB.get(id);
    }

    public List<Student> readAll() {
        return studentDB.values().stream().toList();
    }

    public Long updateAll(Long id, Student student) {
        if (studentDB.containsKey(id)) {
            studentDB.put(id, student);
        }
        return id;
    }

    public Long update(Long id, Student student) {
        if (studentDB.containsKey(id)){
            Student updatedStudent = studentDB.get(id);
            if (student.getId() != null){
                updatedStudent.setId(student.getId());
            }
            if (student.getName() != null){
                updatedStudent.setName(student.getName());
            }
            if (student.getIntroduction() != null){
                updatedStudent.setIntroduction(student.getIntroduction());
            }
            studentDB.put(id, updatedStudent);

        }
        return id;
    }

    public Long deleteById(Long id) {
        studentDB.remove(id);
        return id;
    }

    public void deleteAllById() {
        studentDB.clear();
    }

}
