package vn.edu.hcmut.cse.adse.lab.controller;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // GET ALL
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.save(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable String id,
            @RequestBody Student student) {
        return service.update(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        service.delete(id);
    }
}
