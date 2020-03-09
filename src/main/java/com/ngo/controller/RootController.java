package com.ngo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ngo.model.MyUser;
import com.ngo.service.UserService;

@Controller
public class RootController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String redirectHome() {
		return "redirect:/users";
	}
	
	@GetMapping("/403")
	public String unauthorized(Model model) {
		model.addAttribute("unauthorized", "true");
		return "main";
	}
	
	public MyUser getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(auth.getName());
	}
}
