package com.tunehub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin-home")
    public String adminHome(Model model) {
        String username = "John"; // Replace with your actual username retrieval logic
        model.addAttribute("username", username);
        return "admin-home";
    }
}
