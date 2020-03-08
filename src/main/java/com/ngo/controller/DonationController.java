package com.ngo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ngo.service.CartService;
import com.ngo.service.GiftService;

@Controller
public class DonationController {

	@Autowired
	CartService cartService;
	
	@Autowired
	GiftService giftService;
	
	@GetMapping("/donations")
	public String getAllUsers(Model model){
		model.addAttribute("gifts", giftService.getGifts());
		model.addAttribute("admin", "true");
		model.addAttribute("donationList", "true");
		return "main";
	}
}
