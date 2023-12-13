package com.sidharth.customer.controllers;

import com.sidharth.customer.model.Customer;
import com.sidharth.customer.service.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public String homePage(Model model, @SessionAttribute("token") String token) {
        List<Customer> customers = customerService.getCustomerList(token);
        model.addAttribute("customers", customers);
        model.addAttribute("token", token);
        return "home";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam String customerId, @SessionAttribute("token") String token) {
        customerService.deleteCustomer(customerId, token);
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String street,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String customerId,
            @SessionAttribute("token") String token
    ) {
        customerService.updateCustomer(
                firstName, lastName, street, address, city, state, email, phone, customerId, token
        );
        return "redirect:/home";
    }
}
