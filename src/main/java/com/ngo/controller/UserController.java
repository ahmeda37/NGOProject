package com.ngo.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ngo.bean.AddForm;
import com.ngo.bean.EditForm;
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
		model.addAttribute("userList", "true");
		return "main";
	}
	
	@GetMapping("/users/add")
	public String renderAddUser(Model model) {
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("addForm",new AddForm());
		model.addAttribute("addUserForm", "true");
		return "main";
	}

	@PostMapping("/users/add")
	public String processUser(@Valid AddForm addForm, BindingResult bindingResult, Model model) {
		MyUser existing = userService.getUserByEmail(addForm.getEmail());		
		if(bindingResult.hasErrors() || existing != null) {
			model.addAttribute("curUser", getLoggedInUser());
			model.addAttribute("addUserForm", "true");
			if(existing != null) {
				bindingResult.addError(new FieldError("addUniqueEmail","email","Email Is already used."));

			}
			return "main";
		}
		MyUser user = addForm.setUser();
		user.setHashedPassword(encoder.encode(user.getHashedPassword()));
		userService.addUser(user);
		return "redirect:/users";
	}
	
	//-----------------------------------------------------------------------------

	@GetMapping("/users/edit/{userId}")
	public String renderEditUser(@PathVariable String userId, Model model) {
		MyUser u = userService.getUserById(Long.parseLong(userId));
		EditForm ef = new EditForm();
		ef.setForm(u);
		model.addAttribute("editForm", ef);
		model.addAttribute("curUser", getLoggedInUser());
		model.addAttribute("editUser", "true");
		return "main";
	}
	
	@PostMapping("/users/edit")
	public String processEditUser(@Valid EditForm editForm, BindingResult bindingResult, Model model) {
		MyUser user = userService.getUserById(editForm.getUserId());
		MyUser existing = userService.getUserByEmail(editForm.getEmail());
		if(bindingResult.hasErrors()) {
			model.addAttribute("curUser", getLoggedInUser());
			model.addAttribute("editUser", "true");
			if(existing != null && !editForm.getEmail().equalsIgnoreCase(user.getEmail())) {
				bindingResult.addError(new FieldError("addUniqueEmail","email","Email Is already used. Please Enter Pevious or New Email."));
			}
			return "main";
		}else if(existing != null && !editForm.getEmail().equalsIgnoreCase(user.getEmail())) {
			bindingResult.addError(new FieldError("addUniqueEmail","email","Email Is already used. Please Enter Pevious or New Email."));
			model.addAttribute("curUser", getLoggedInUser());
			model.addAttribute("editUser", "true");
			return "main";
		}
		userService.updateUser(editForm.getUser(user));
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
