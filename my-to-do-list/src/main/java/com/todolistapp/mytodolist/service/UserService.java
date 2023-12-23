package com.todolistapp.mytodolist.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolistapp.mytodolist.repository.UserRepository;
import com.todolistapp.mytodolist.model.User;

@Service
public class UserService  {
	
	@Autowired
	UserRepository user_repository;
	

	
	
	public List<User> get_users(){
		return (List<User>) user_repository.findAll();
	}
	
	public void delete_user_by_id(int id) {
		user_repository.deleteById(id);
		
	}
	
	public User save_user(User user) {
		return user_repository.save(user);
	}
	
	
	public Optional<User> get_user_by_id(int id){
		return user_repository.findById(id);
	}
	
	public Optional<User> get_user_by_username(String username){
		return user_repository.findByUsername(username);
	}
	

	
}
