package com.hcmut.student_management.repository;

import com.hcmut.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, String> {
    List<Student> findByNameContainingIgnoreCase(String name);
}
