package com.example.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbooking.dao.UserRepository;
import com.example.ticketbooking.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
public boolean InsertUsertable(String email, String password){
		
		if (email !=null && password != null){
			
			User user = new User(email, password);
			userRepository.save(user);
			return true;
		}
		return false;
		
	}
	
public boolean SelectUsertable(String email, String password){
		
		if (email !=null && password != null){
			
			User user = userRepository.findOne(email);
			
			if (user.getPassword() == password)
				return true;
		}
		return false;
		
	}
	
}
