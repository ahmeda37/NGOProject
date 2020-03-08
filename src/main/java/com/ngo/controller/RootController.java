package com.ngo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping("/")
	public String redirectHome() {
		return "redirect:/users";
	}
	
	@GetMapping("/403")
	public String unauthorized(Model model) {
		model.addAttribute("unauthorized", "true");
		return "main";
	}
}
