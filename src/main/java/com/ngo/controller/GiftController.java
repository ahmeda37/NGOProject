package com.ngo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ngo.model.Address;
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
		model.addAttribute("giftView", "true");
		return "main";
	}
	
	@GetMapping("/gifts/confirm")
	public String confirmView(Model model) {
		model.addAttribute("user", getLoggedInUser());
		model.addAttribute("confirmView", "true");
		return "main";
	}
	
	@PostMapping("gifts/confirm")
	public String updateProfile(Model model, @ModelAttribute Address address) {
		MyUser curUser = getLoggedInUser();
		Address add = setAddr(curUser.getAddress(), address);
		curUser.setAddress(add);
		userService.updateUser(curUser);
		model.addAttribute("curUser", curUser);
		model.addAttribute("giftAmount", "true");
		model.addAttribute("types", donationService.getDonationTypes());
		return "main";
	}
		
	public Address setAddr(Address cur, Address newAdd) {
		if(newAdd.getAddress1() != null && !newAdd.getAddress1().isBlank()) {cur.setAddress1(newAdd.getAddress1());}
		if(newAdd.getAddress2() != null && !newAdd.getAddress2().isBlank()) {cur.setAddress2(newAdd.getAddress2());}
		if(newAdd.getCity() != null && !newAdd.getCity().isBlank()) {cur.setCity(newAdd.getCity());}
		if(newAdd.getState() != null && !newAdd.getState().isBlank()) {cur.setState(newAdd.getState());}
		if(newAdd.getCountry() != null && !newAdd.getCountry().isBlank()) {cur.setCountry(newAdd.getCountry());}
		if(newAdd.getZip() != null && !newAdd.getZip().isBlank()) {cur.setZip(newAdd.getZip());}
		if(newAdd.getUrbanization() != null && !newAdd.getUrbanization().isBlank()) {cur.setUrbanization(newAdd.getUrbanization());}
		return cur;
	}
	public MyUser getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(auth.getName());
	}

}
