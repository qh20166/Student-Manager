package vn.edu.hcmut.cse.adse.lab.service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // READ ALL
    public List<Student> getAll() {
        return repository.findAllByOrderByIdAsc();
    }

    // READ BY ID
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE
    public Student save(Student student) {
        if (repository.existsById(student.getId())) {
            throw new RuntimeException("Student ID already exists");
        }
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
        return repository.findByNameContainingIgnoreCaseOrderByIdAsc(keyword);
    }

}
