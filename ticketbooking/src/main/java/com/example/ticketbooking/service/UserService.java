package com.example.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ticketbooking.dao.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;
}
