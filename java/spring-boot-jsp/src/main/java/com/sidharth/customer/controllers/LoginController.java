package com.sidharth.customer.controllers;

import com.sidharth.customer.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("token")
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        String token = new LoginService().authenticate(email, password);

        if (token != null) {
            model.addAttribute("token", token);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
}
