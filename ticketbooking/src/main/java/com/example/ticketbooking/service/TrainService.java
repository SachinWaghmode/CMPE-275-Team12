package com.example.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ticketbooking.dao.TrainRepository;

public class TrainService {
	
	@Autowired
	private TrainRepository trainRepository;

}
