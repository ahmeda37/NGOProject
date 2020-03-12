package com.ngo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ngo.bean.GiftForm;
import com.ngo.model.Address;
import com.ngo.model.Cart;
import com.ngo.model.Gift;
import com.ngo.model.MyUser;
import com.ngo.service.CartService;
import com.ngo.service.DonationService;
import com.ngo.service.GiftService;
import com.ngo.service.UserService;

@Controller
public class GiftController {

	@Autowired
	UserService userService;
	
	@Autowired
	DonationService donationService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	GiftService giftService;

	@Autowired
	private JavaMailSender javaMailSender;
	
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
		
		GiftForm form = new GiftForm();
		form.setDonationTypes(donationService.getDonationTypes());
		
		model.addAttribute("curUser", curUser);
		model.addAttribute("giftAmount", "true");
		model.addAttribute("types", donationService.getDonationTypes());
		model.addAttribute("form", form);
		return "main";
	}
	
	@PostMapping("gifts/cart")
	public String showCart(Model model, @ModelAttribute GiftForm gifts) {
		ArrayList<Gift> gift = gifts.getGiftList();
		Iterator<Gift> it = gift.iterator();
		Gift g;
		float total = 0;
		while(it.hasNext()) {
			g = it.next();
			if(g.getGiftAmount() <= 0 || g.getQuantity() <= 0) {
				it.remove();
			} else {
				g.setDonationType(donationService.getDonationTypeById(g.getDonationType().getDonationTypeId()));
				total += g.getGiftAmount() * g.getQuantity();
			}
		}
		
		HashSet<Gift> giftSet = new HashSet<Gift>(gift);
		giftService.saveAll(giftSet);
		
		Cart c = new Cart();
		c.setUser(getLoggedInUser());
		c.setGifts(giftSet);
		c.setTotal(total);
		c.setProcessed(false);
		
		cartService.addCart(c);
		
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("cartView", "true");
		model.addAttribute("cart", c);
		return "main";
	}
	
	@GetMapping("/gifts/cart/remove/{cartId}/{giftId}")
	public String removeGift (@PathVariable String giftId, @PathVariable String cartId, Model model) {
		Gift g = giftService.getGiftById(Long.parseLong(giftId));
		float total = g.getGiftAmount() * g.getQuantity();
		giftService.deleteGift(g.getGiftId());
		Cart c = cartService.getCartById(Long.parseLong(cartId));
		c.setTotal(c.getTotal()-total);
		cartService.updateCart(c);
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("cartView", "true");
		model.addAttribute("cart", c);
		return "main";
	}
	
	@PostMapping("/gifts/cart/process/{cartId}")
	public String processCart(@PathVariable String cartId, Model model) {
		Date date = new Date(System.currentTimeMillis());
		Cart c = cartService.getCartById(Long.parseLong(cartId));
		c.setProcessed(true);
		c.setDate(date);
		cartService.updateCart(c);
		
		sendEmail(c);
		
		return "redirect:/";
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
	public void sendEmail(Cart c) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(c.getUser().getEmail());
		
		msg.setSubject("Your Donation on " + c.getDate());
		msg.setText(c.toString());
		
		javaMailSender.send(msg);
	}
}
