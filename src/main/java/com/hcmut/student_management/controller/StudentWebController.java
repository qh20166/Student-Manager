package com.hcmut.student_management.controller;

import com.hcmut.student_management.entity.Student;
import com.hcmut.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    // http://localhost:8080/students
    @GetMapping("/students")
    public String getAllStudents(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }

        model.addAttribute("dsSinhVien", students);

        return "students";
    }
}
