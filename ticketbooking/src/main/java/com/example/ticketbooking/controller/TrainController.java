package com.example.ticketbooking.controller;

import java.security.Principal;
import java.util.Date;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.ticketbooking.service.TrainService;

@RestController
@Transactional
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@RequestMapping(value = "/train/search", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> searchTrain(Principal principal,
	    		
	    		@RequestParam(value="departuredate",required=true) Date departureDate,
	    		@RequestParam(value="departuretime",required=true) String departureTime,
	    		//@RequestParam(value="arrivaltime",required=true) String arrivalTime,
	    		@RequestParam(value="fromstation",required=true) String fromStation,
	    		@RequestParam(value="tostation",required=true) String toStation,
	    		@RequestParam(value="noofconnections") int noOfConnections,
	    		@RequestParam(value="nooftickets") int noOfTickets,
	    		@RequestParam(value="tickettype") String ticketType,
	    		@RequestParam(value="traintype") String trainType,
	    		@RequestParam(value="returntripdate",required=true) Date returnTripDate,
	    		@RequestParam(value="returntriptime",required=true) String returnTripTime
	    		) {
		 System.out.println("Inside GET Request");
	    	//String email = principal.getName();
		 String email = "sachinwaghmode57@gmail.com";
	     
		 return trainService.searchForTrain(email,departureDate, departureTime, fromStation, toStation, noOfConnections, noOfTickets, ticketType, trainType, returnTripDate, returnTripTime);
	    }

}