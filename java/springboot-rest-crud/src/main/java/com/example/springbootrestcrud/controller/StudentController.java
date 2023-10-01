package com.example.springbootrestcrud.controller;

import com.example.springbootrestcrud.model.StudentModel;
import com.example.springbootrestcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Declares the class as Controller Class
@RequestMapping("/student") // specifies the main path of API
public class StudentController {

    @Autowired // creates object of a class
    private StudentService studentService;

    @PostMapping("/addStudent") // API POST method sub-path
    public String addMethod(@RequestBody StudentModel studentModelClass) {
        studentService.createStudent(studentModelClass);
        return "Student added successfully!!";
    }

    @PostMapping("/addStudentList")
    public String addStudentList(@RequestBody List<StudentModel> studentList) {
        studentService.addStudents(studentList);
        return "List of students added successfully!!";
    }

    @GetMapping("/getAll")
    public List<StudentModel> getAll() {
        return studentService.getStudents();
    }

    @GetMapping("/get/{id}")
    public StudentModel getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student with Id: " + id + " deleted";
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody StudentModel studentModel){
        boolean idFound = studentService.updateStudent(id, studentModel);
        if (idFound) {
            return "Student with Id: " + id + " updated";
        } else {
            return "Student with Id: " + id + " is unavailable";
        }
    }

}
