package com.tunehub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

public class CustomerHomeController {

	 @Autowired
	    private UsersService userService;

	    @GetMapping("/home")
	    public String home(Model model, Users user) {
	        String userEmail = user.getUsername();
	        String username = userService.getUsername(userEmail);
	        model.addAttribute("username", username);
	        return "home";
	    }
	    
}
