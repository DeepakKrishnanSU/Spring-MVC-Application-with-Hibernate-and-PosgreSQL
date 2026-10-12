package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.dao.StudentDAO;
import com.student.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Add student
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {

        studentDAO.save(student);

        return "redirect:/students/list";
    }

    // Display all students
    @GetMapping("/list")
    public String listStudents(Model model) {

        model.addAttribute(
            "students",
            studentDAO.getAllStudents()
        );

        return "students";
    }

    // Search student
    @GetMapping("/search")
    public String searchStudent(
            @RequestParam("regno") int regno,
            Model model) {

        Student student = studentDAO.getStudent(regno);

        model.addAttribute("student", student);

        return "search";
    }

    // Show update page
    @GetMapping("/edit/{regno}")
    public String editStudent(
            @PathVariable int regno,
            Model model) {

        Student student = studentDAO.getStudent(regno);

        model.addAttribute("student", student);

        return "edit";
    }

    // Update student
    @PostMapping("/update")
    public String updateStudent(
            @ModelAttribute Student student) {

        studentDAO.update(student);

        return "redirect:/students/list";
    }

    // Delete student
    @GetMapping("/delete/{regno}")
    public String deleteStudent(
            @PathVariable int regno) {

        studentDAO.delete(regno);

        return "redirect:/students/list";
    }
}