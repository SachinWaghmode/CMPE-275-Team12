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

	
	
	
public ResponseEntity<?> searchForTrain(String email,Date departureDate,String departureTime, String fromStation, String toStation,int noOfConnections,int noOfTickets, String ticketType, String trainType){
		
		
		List<Train> trains = new ArrayList<>();
		List<Train> availableTrains = new ArrayList<>();
		Map<Integer, List<Train>> tickets = new HashMap<>();
	    trainRepository.findAll().forEach(trains::add);
	    int count =0;
		
		
	    for(int i=0; i<trains.size() && tickets.size() <= 5; i++){
	    	System.out.println("Train ID"+trains.get(i).getId());
	    	System.out.println("dep Time"+trains.get(i).getArrivalTime());
	    	System.out.println("Capacity"+trains.get(i).getCapacity());
	    	System.out.println("From Station"+trains.get(i).getFromStation());
	    	System.out.println("To Station"+trains.get(i).getToStation());
	    	System.out.println("Train Type"+trains.get(i).getTrainType());
	    	System.out.println("Train Number"+trains.get(i).getTrainNumber());
	    	System.out.println("dep date"+trains.get(i).getDepartureDate());
	    	
	    	System.out.println("Here is type of train : "+trainType+"database value: "+trains.get(i).getTrainType());
	    	
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    	LocalDate localDate = LocalDate.now();
	    	LocalDate nextweek = localDate.plusWeeks(1);
	    	
	    	int atime = Integer.parseInt(departureTime);
	    	atime = atime+100;
	    	
	    	int getTime = Integer.parseInt(trains.get(i).getArrivalTime());
	    	
	        // Check the capacity and departure time greater than 1 hour from now and date 
	    	if (trains.get(i).getCapacity() >= noOfTickets && (atime <= getTime) || ( localDate.equals(trains.get(i).getDepartureDate())   ) ){
	    		
	    			// check train type = Express or Regular 
	    		//	if (trains.get(i).getTrainType().equals(trainType)){
	    				
	    				if (noOfConnections == 0){
	    				
	    			       tickets.put(count++, checkFromTo(trains.get(i), fromStation, toStation));
	    				}
	    				else if (noOfConnections >= 1){
	    				
	    				//tickets = checkFromToConnections(trains.get(i), fromStation, toStation);
	    				
	    				if(trains.get(i).getFromStation().compareTo(fromStation) <= 0){
	    					availableTrains.add(trains.get(i));
	    					
	    					String tempfromStation = fromStation;
	    					fromStation = trains.get(i).getToStation();
	    					String arrives = trains.get(i).getArrivalTime();
	    				
	    				     if ((trains.get(i).getToStation().compareTo(toStation) >= 0)){
	    				    	
	    				    	 tickets.put(count++, availableTrains);
	    				    	 fromStation = tempfromStation;
	    				     }
	    				     
	    				   
	    				}
	    			}
	    			
	    	//	}
	    	}
	    }
        //Collections.sort(availableTrains);
		return new ResponseEntity<>(tickets,HttpStatus.OK);
		
		
		
	}
   public static  List<Train> checkFromTo(Train trains, String fromStation, String toStation){
	   
	   int count =0;
		List<Train> availableTrains = new ArrayList<>();
		Map<Integer, List<Train>> tickets = new HashMap<>();
	// check direct train with 0 connections
		if((trains.getFromStation().compareTo(fromStation) <= 0) && (trains.getToStation().compareTo(toStation) >= 0)){
			       availableTrains.add(trains);
			      // tickets.put(count++, availableTrains);
			}
		
		return availableTrains;
   }
   
   public static Map<Integer, List<Train>> checkFromToConnections(Train trains, String fromStation, String toStation){
	  
		List<Train> availableTrains = new ArrayList<>();
		Map<Integer, List<Train>> tickets = new HashMap<>();
	   // check first connection
	  /* 
		if(trains.getFromStation().compareTo(fromStation) <= 0){
			availableTrains.add(trains);
			String tempfromStation = trains.getToStation();
			String arrives = trains.getArrivalTime();
			int j = i;
			 while (trains.get(j).getFromStation().compareTo(tempfromStation)<= 0){
				 j++;
			 }
		     if ((trains.get(j).getToStation().compareTo(toStation) >= 0)){
		    	 availableTrains.add(trains.get(j));
		    	 tickets.put(count++, availableTrains);
		     }
		     
		     // check second connection
		     else if(trains.get(j).getFromStation().compareTo(fromStation) <= 0){
				availableTrains.add(trains.get(j));
				 tempfromStation = trains.get(j).getToStation();
				 arrives = trains.get(j).getArrivalTime();
				 while (trains.get(j).getFromStation().compareTo(tempfromStation)<= 0){
					 j++;
				 }
			     if ((trains.get(j).getToStation().compareTo(toStation) >= 0)){
			    	 availableTrains.add(trains.get(j));
			    	tickets.put(count++, availableTrains);
			     }
		}
		}
		*/
		return tickets;
   }
   
   
   
}