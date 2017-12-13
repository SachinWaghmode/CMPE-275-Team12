package com.example.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbooking.dao.UserRepository;
import com.example.ticketbooking.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	
}
