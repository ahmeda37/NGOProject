package com.ngo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.ngo.model.Address;
import com.ngo.model.MyUser;
import com.ngo.service.UserService;

@Controller
public class UserController{

	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@GetMapping("/users")
	public String getAllUsers(Model model){
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("admin", "true");
		model.addAttribute("userList", "true");
		return "main";
	}
	
	@GetMapping("/users/add")
	public String renderAddUser(Model model) {
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("admin", "true");
		model.addAttribute("addUserForm", "true");
		return "main";
	}
	
	@PostMapping("/users/addAddress/{userId}")
	public String processAddress(@ModelAttribute Address address, @PathVariable String userId, Model model) {
		MyUser u = userService.getUserById(Integer.parseInt(userId));
		u.setAddress(address);
		userService.updateUser(u);
		return "redirect:/";
	}
	
	@PostMapping("/users/add")
	public String processUser(@ModelAttribute MyUser user, Model model) {
		String hashedPassword = encoder.encode(user.getHashedPassword()).toString();
		user.setHashedPassword(hashedPassword);
		userService.addUser(user);
		model.addAttribute("admin", "true");
		model.addAttribute("user", user);
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("addAddress", "true");
		return "main";
	}
	
	//-----------------------------------------------------------------------------

	@GetMapping("/users/edit/{userId}")
	public String renderEditUser(@PathVariable String userId, Model model) {
		MyUser u = userService.getUserById(Long.parseLong(userId));
		model.addAttribute("admin", "true");
		model.addAttribute("user", u);
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("editUser", "true");
		return "main";
	}
	
	@PostMapping("/users/edit/{userId}")
	public String processEditUser(@ModelAttribute MyUser user, @PathVariable String userId, Model model) {
		MyUser user1 = userService.getUserById(Long.parseLong(userId));
		Address a = user1.getAddress();
		if(!user1.getHashedPassword().equalsIgnoreCase(user.getHashedPassword())) {
			String hashedPassword = encoder.encode(user.getHashedPassword()).toString();
			user.setHashedPassword(hashedPassword);	
		}
		user.setAddress(a);
		userService.updateUser(user);
		model.addAttribute("admin", "true");
		model.addAttribute("user",user);
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("editAddress", "true");
		return "main";
	}
	
	@PostMapping("/users/editAddress/{userId}")
	public String processEditUser(@ModelAttribute Address address, @PathVariable String userId, Model model) {
		MyUser u = userService.getUserById(Long.parseLong(userId));
		u.setAddress(address);
		userService.updateUser(u);
		if(!u.getAdmin() && u == getLoggedInUser()) {
	        SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/";
	}
	
	@GetMapping("/users/delete/{userId}")
	public String processDeleteUser(@PathVariable String userId) {
		userService.deleteUser(Long.parseLong(userId));
		return "redirect:/";
	}
	
	public MyUser getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(auth.getName());
	}

}
