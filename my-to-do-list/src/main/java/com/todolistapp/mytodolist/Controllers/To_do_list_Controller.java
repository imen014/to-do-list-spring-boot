package com.todolistapp.mytodolist.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todolistapp.mytodolist.model.User;
import com.todolistapp.mytodolist.service.UserService;

@Controller
public class To_do_list_Controller {
	
	@Autowired
	 UserService  user_service;

	
	@GetMapping("/inscription")
	public String inscription() {
		return "form_inscription";
		
	}
	
	@PostMapping("/inscription")
	public String traiter_inscription(Model model,
			@RequestParam String username,
			@RequestParam String email,
			@RequestParam String password) {
		
		User user = new User(username,email, password);
		model.addAttribute("user",user );
		user_service.save_user(user);
		
		
		return "user_saved";		
		
		
	}
	
	
	@GetMapping("/get_users")
	public String get_users(Model model) {
		List users =   (List) user_service.get_users();
		model.addAttribute("users", users);
		return "get_users_from_db";
		
	}
	
	@GetMapping("/delete_user")
	public String delete_user() {
		return "form_delete";
		
	}
	
	@PostMapping("/delete_user")
	public String traiter_inscription(Model model,
			@RequestParam int id) {
		
		model.addAttribute("id",id);
		user_service.delete_user_by_id(id);
		
		
		return "user_deleted";		
		
		
	}
	
	
	@GetMapping("/modify_user")
	public String modify_user() {
		return "form_modify";
		
	}
	
	
	@PostMapping("/modify_user")
	public String modify_user(Model model, 
			@RequestParam String username,
			@RequestParam String newUsername,
			@RequestParam String newEmail,
			@RequestParam String newPassword) {
		
		
		Optional<User> optional_user = user_service.get_user_by_username(username);
		
		if(optional_user.isPresent()) {
			
	        User user = optional_user.get();
	        user.setUsername(newUsername);
	        user.setEmail(newEmail);
	        user.setPassword(newPassword);
	        
			user_service.save_user(user);
			model.addAttribute("user",user);
			return "user_modified";

		}
		
		else {
			return "user_not_found";
			
		}
		
		
		
		
		
	}

}
