package com.example.ticketbooking.controller;

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

import com.example.ticketbooking.service.BookingService;

@RestController
@Transactional
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = "/train/purchase", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBooking(
    	@RequestParam(value="email",required=true) String email,
		@RequestParam(value="departuredate",required=true) Date departureDate,
		@RequestParam(value="departuretime",required=true) String departureTime,
		@RequestParam(value="arrivaltime",required=true) String arrivalTime,
		@RequestParam(value="fromstation",required=true) String fromStation,
		@RequestParam(value="tostation",required=true) String toStation,
		@RequestParam(value="noofconnections") int noOfConnections,
		@RequestParam(value="nooftickets") int noOfTickets
    		){
		return bookingService.purchaseTicket(email,departureDate,departureTime,arrivalTime,fromStation,toStation,noOfConnections,noOfTickets);
    }
	
}
