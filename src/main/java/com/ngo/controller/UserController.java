package com.ngo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ngo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public String getAllUsers(Model model){
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("admin", "false");
		return "users";
	}
}
