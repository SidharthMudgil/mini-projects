package main.java.com.example.springbootems.controller;

import com.example.springbootems.model.Employee;
import com.example.springbootems.repository.EMSRepository;
import com.example.springbootems.service.EMSService;
import com.example.springbootems.service.EMSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EMSController {

    @Autowired
    private EMSServiceImpl emsService;

    @GetMapping("/ems")
    public String listEmployees(Model model) {
        model.addAttribute("employees", emsService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/ems/add")
    public String addEmployee(Model model) {
        // Employee object to hold data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    @PostMapping("/ems/add")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        emsService.saveStudent(employee);
        return "redirect:/ems";
    }
}