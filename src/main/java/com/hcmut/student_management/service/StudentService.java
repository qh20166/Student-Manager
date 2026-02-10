package com.hcmut.student_management.service;

import com.hcmut.student_management.entity.Student;
import com.hcmut.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // READ ALL
    public List<Student> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE
    public Student save(Student student) {
        return repository.save(student);
    }

    // UPDATE
    public Student update(String id, Student newStudent) {
        Student student = repository.findById(id).orElse(null);

        if (student == null) return null;

        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());
        student.setAge(newStudent.getAge());

        return repository.save(student);
    }

    // DELETE
    public void delete(String id) {
        repository.deleteById(id);
    }

    // SEARCH
    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

}
