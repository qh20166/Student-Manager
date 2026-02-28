package vn.edu.hcmut.cse.adse.lab.controller;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService service;

    // 1. HOME
    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    // 2. LIST + SEARCH
    @GetMapping({"/students", "/students/"})
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students = (keyword != null && !keyword.isEmpty())
                ? service.searchByName(keyword)
                : service.getAll();

        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // 3. DETAIL
    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-detail";
    }

    // 4. FORM CREATE
    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // 5. CREATE
    @PostMapping("/students")
    public String saveStudent(Student student, Model model) {
        try {
            service.save(student);
            return "redirect:/students";
        } catch (RuntimeException ex) { // ✅ This matches what the Service is throwing
            model.addAttribute("errorMessage", ex.getMessage()); // Uses "Student ID already exists"
            model.addAttribute("student", new Student());
            return "student-form";
        }
    }

    // 6. FORM EDIT
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-form";
    }

    // 7. UPDATE
    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable String id, Student student) {
        service.update(id, student);
        return "redirect:/students";
    }

    // 8. DELETE
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }
}