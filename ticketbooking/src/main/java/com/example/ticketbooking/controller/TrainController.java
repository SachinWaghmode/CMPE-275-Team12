package com.example.ticketbooking.controller;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.ticketbooking.service.TrainService;

@RestController
@Transactional
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@RequestMapping(value = "/train/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> searchTrain(@PathVariable Long id) {
		 System.out.println("Inside GET Request");
	    	
	        return trainService.searchForTrain(id);
	    }

}
