package com.ngo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.ngo.model.Address;
import com.ngo.model.User;
import com.ngo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public String getAllUsers(Model model){
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("admin", "true");
		model.addAttribute("userList", "true");
		return "main";
	}
	
	@GetMapping("/users/add")
	public String renderAddUser(Model model) {
		model.addAttribute("admin", "true");
		model.addAttribute("addUserForm", "true");
		return "main";
	}
	
	@PostMapping("/users/addAddress/{userId}")
	public String processAddress(@ModelAttribute Address address, @PathVariable String userId, Model model) {
		User u = userService.getUserById(Integer.parseInt(userId));
		u.setAddress(address);
		userService.updateUser(u);
		return "redirect:/users";
	}
	
	@PostMapping("/users/add")
	public String processUser(@ModelAttribute User user, Model model) {
		userService.addUser(user);
		model.addAttribute("admin", "true");
		model.addAttribute("user", user);
		model.addAttribute("addAddress", "true");
		return "main";
	}
	
	//-----------------------------------------------------------------------------

	@GetMapping("/users/edit/{userId}")
	public String renderEditUser(@PathVariable String userId, Model model) {
		User u = userService.getUserById(Long.parseLong(userId));
		model.addAttribute("admin", "true");
		model.addAttribute("user", u);
		model.addAttribute("editUser", "true");
		return "main";
	}
	
	@PostMapping("/users/edit/{userId}")
	public String processEditUser(@ModelAttribute User user, @PathVariable String userId, Model model) {
		Address a = userService.getUserById(Long.parseLong(userId)).getAddress();
		user.setAddress(a);
		userService.updateUser(user);
		model.addAttribute("admin", "true");
		model.addAttribute("user",user);
		model.addAttribute("editAddress", "true");
		return "main";
	}
	
	@PostMapping("/users/editAddress/{userId}")
	public String processEditUser(@ModelAttribute Address address, @PathVariable String userId, Model model) {
		User u = userService.getUserById(Long.parseLong(userId));
		u.setAddress(address);
		userService.updateUser(u);
		return "redirect:/users";
	}
	
	@GetMapping("/users/delete/{userId}")
	public String processDeleteUser(@PathVariable String userId) {
		userService.deleteUser(Long.parseLong(userId));
		return "redirect:/users";
	}
}
