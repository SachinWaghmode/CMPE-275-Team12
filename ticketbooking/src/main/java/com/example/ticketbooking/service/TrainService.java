package com.example.ticketbooking.service;

import java.util.ArrayList;
import java.util.List;

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

	
	
	
public ResponseEntity<?> searchForTrain(Long id){
		
		System.out.println("inside getPlayer()");
		//Train train = trainRepository.findOne(id);
		List<Train> trains = new ArrayList<>();
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
	    }
		/*if (trains==null){
			return new ResponseEntity<>("404-Train not Found",HttpStatus.NOT_FOUND);
		}
		if(trains!=null){
			System.out.println("inside getTrain() if");
			try{
			
			}
			catch(Exception e){
				return new 	ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
			}
		}*/
		return new ResponseEntity<>(trains,HttpStatus.OK);
		
		
		
	}
}
