package com.example.ticketbooking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ticketbooking.dao.TrainRepository;
import com.example.ticketbooking.model.Train;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepository trainRepository;

	
	
	
public ResponseEntity<?> searchForTrain(String email,Date departureDate,String departureTime, String arrivalTime, String fromStation, String toStation,int noOfConnections,int noOfTickets){
		
		System.out.println("inside getPlayer()");
		//Train train = trainRepository.findOne(id);
		List<Train> trains = new ArrayList<>();
		List<Train> availableTrains = new ArrayList<>();
	    trainRepository.findAll().forEach(trains::add);
	    
	    for(int i=0; i<trains.size(); i++){
	    	System.out.println("Train ID"+trains.get(i).getId());
	    	System.out.println("Train A Time"+trains.get(i).getArrivalTime());
	    	System.out.println("Train Number"+trains.get(i).getCapacity());
	    	System.out.println("Train Number"+trains.get(i).getFromStation());
	    	System.out.println("Train Number"+trains.get(i).getToStation());
	    	System.out.println("Train Number"+trains.get(i).getTrainType());
	    	System.out.println("Train Number"+trains.get(i).getTrainNumber());
	    	System.out.println("Train Number"+trains.get(i).getDepartureDate());
	    	
	    	
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    	LocalDate localDate = LocalDate.now();
	    	LocalDate nextweek = localDate.plusWeeks(1);
	    	
	    	int atime = Integer.parseInt(arrivalTime);
	    	atime = atime+100;
	    	
	    	int getTime = Integer.parseInt(trains.get(i).getArrivalTime());
	    	
	    
	    	if (trains.get(i).getCapacity() >= noOfTickets && (atime <= getTime) || ( localDate.equals(trains.get(i).getDepartureDate())  ) ){
	    		if((trains.get(i).getFromStation().compareTo(fromStation) <= 0) && (trains.get(i).getToStation().compareTo(toStation) >= 0)){
	    			
	    			availableTrains.add(trains.get(i));
	    		}
	    	}
	    }
        //Collections.sort(availableTrains);
		return new ResponseEntity<>(availableTrains,HttpStatus.OK);
		
		
		
	}
}