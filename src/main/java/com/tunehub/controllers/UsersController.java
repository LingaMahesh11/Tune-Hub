package com.tunehub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

	@Autowired
	UsersService service;
	
	
	//User registration with unique email address
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean userStatus = service.emailExists(user.getEmail());
		
		if(userStatus == false) {
			service.addUser(user);
			System.out.println("user added successfully");
		}
		else {
			System.out.println("User already exists");
		}
		
		return "index";
	}
	
	//Redirecting the user as per their role (Admin or Customer)
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		
		if(service.validateUser(email,password) == true) {
			String role = service.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				Users user = service.getUser(email);
				String username = user.getUsername();
				model.addAttribute("username", username);
				return "adminHome";
			}
			else {
				Users user = service.getUser(email);
				boolean userStatus = user.isPremium();
				String username = user.getUsername();
				model.addAttribute("isPremium", userStatus);
				model.addAttribute("username", username);
				return "customerHome";
			}
		}
		else {
			return "login"; 
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	
	
}
