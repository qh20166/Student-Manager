package com.hcmut.student_management.controller;

import com.hcmut.student_management.entity.Student;
import com.hcmut.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // LIST + SEARCH
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        // Service lúc này đã tự động trả về list đã sắp xếp ID từ A-Z
        List<Student> students = (keyword != null && !keyword.isEmpty())
                ? service.searchByName(keyword)
                : service.getAll();

        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // DETAIL
    @GetMapping("/{id}")
    public String viewStudent(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-detail";
    }

    // FORM CREATE
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // CREATE
    @PostMapping
    public String saveStudent(Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // FORM EDIT
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-form";
    }

    // UPDATE
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable String id, Student student) {
        service.update(id, student);
        return "redirect:/students";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }
}