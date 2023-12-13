package com.sidharth.customer.controllers;

import com.sidharth.customer.model.Customer;
import com.sidharth.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class CreateController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/create")
    public String createPage(Model model, @SessionAttribute("token") String token) {
        model.addAttribute("token", token);
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String street,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String email,
            @RequestParam String phone,
            @SessionAttribute("token") String token
    ) {
        Boolean isSuccessful = customerService.createCustomer(
                firstName, lastName, street, address, city, state, email, phone, token
        );

        if (isSuccessful) {
            return "redirect:/home";
        } else {
            return "redirect:/create";
        }
    }
}
