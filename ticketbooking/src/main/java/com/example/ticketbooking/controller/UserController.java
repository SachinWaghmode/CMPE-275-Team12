package com.example.ticketbooking.controller;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketbooking.model.User;
import com.example.ticketbooking.service.UserService;


@RestController
@Transactional
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity addUser(@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String password){
		if( userService.InsertUsertable(email, password))
		 			return ResponseEntity.ok(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity getUser(@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String password){
		if( userService.SelectUsertable(email, password))
		 			return ResponseEntity.ok(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.POST)
	public ResponseEntity getUser1(@RequestParam(value="title",required=true) String email){
		//if( userService.SelectUsertable(email, password))
		System.out.println("request recveived from article url");
		 			return ResponseEntity.ok(null);
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

}
