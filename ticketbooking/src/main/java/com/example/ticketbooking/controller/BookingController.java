package com.example.ticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ticketbooking.service.BookingService;

public class BookingController {

	@Autowired
	private BookingService bookingService;
}
