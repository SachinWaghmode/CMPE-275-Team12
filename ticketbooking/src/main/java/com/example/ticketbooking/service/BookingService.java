package com.example.ticketbooking.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ticketbooking.dao.BookingRepository;
import com.example.ticketbooking.dao.UserRepository;
import com.example.ticketbooking.model.Booking;
import com.example.ticketbooking.model.User;
@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<?> purchaseTicket(String email,Date departureDate,String departureTime, String arrivalTime, String fromStation, String toStation,int noOfConnections,int noOfTickets){
		
		User user = null;
		if (email != null){
			user = userRepository.findOne(email) ;
		}
		Booking booking = new Booking(user,departureDate,departureTime,arrivalTime,fromStation,toStation,noOfConnections,noOfTickets);
		
		if(departureDate != null  || departureTime != null || arrivalTime !=null || fromStation !=null){
		
		
		}
		bookingRepository.save(booking);
		
		return new ResponseEntity<>(booking, 
				HttpStatus.CREATED);
		
	}

}
