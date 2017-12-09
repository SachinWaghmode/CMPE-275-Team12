package com.example.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ticketbooking.dao.BookingRepository;

public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

}
