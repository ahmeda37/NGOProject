package com.ngo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ngo.model.MyUser;
import com.ngo.service.DonationService;
import com.ngo.service.UserService;

@Controller
public class GiftController {

	@Autowired
	UserService userService;
	
	@Autowired
	DonationService donationService;
	
	@GetMapping("/gifts")
	public String userView(Model model) {
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("types", donationService.getDonationTypes());
		model.addAttribute("admin", "true");
		model.addAttribute("giftView", "true");
		return "main";
	}
	
	public MyUser getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(auth.getName());
	}

}
