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

	
	
	
public ResponseEntity<?> searchForTrain(String email,Date departureDate,String departureTime, String fromStation, String toStation,int noOfConnections,int noOfTickets, String ticketType, String trainType, Date returnTripDate, String returnTripTime){
		
	List<Train> trains = new ArrayList<>();
	Map<Integer, List<Train>> tickets = new HashMap<>();
	Map<Integer, List<Train>> returntickets = new HashMap<>();
	Map<Integer, List<Train>> Roundtickets = new HashMap<>();
	trainRepository.findAll().forEach(trains::add);
    
	if (ticketType.equals("O")){
	 tickets = checkFromToConnections(trains, email, departureDate, departureTime, fromStation,  toStation, noOfConnections, noOfTickets,  ticketType, trainType);
	}else if(ticketType.equals("R")){
	  Roundtickets = checkFromToConnections(trains, email, departureDate, departureTime, fromStation,  toStation, noOfConnections, noOfTickets,  ticketType, trainType);
	  returntickets = checkFromToConnections(trains, email, returnTripDate, returnTripTime,  toStation, fromStation,  noOfConnections, noOfTickets,  ticketType, trainType);
	  
	  tickets.putAll(Roundtickets);
	 // tickets.putAll(returntickets);
	  
	 
	  for(int key : returntickets.keySet()) {
	      List<Train> list2 = returntickets.get(key);
	      List<Train> list3 = tickets.get(key);
	      if(list3 != null) {
	          list3.addAll(list2);
	      } else {
	          tickets.put(key,list2);
	      }
	  }
	  
	  
	  
	  
	  
	  
	  
	}
	
		return new ResponseEntity<>(tickets,HttpStatus.OK);
		
		
		
	}


   public static  List<Train> checkFromTo(Train trains, String fromStation, String toStation){
	   
	   int count =0;
		List<Train> availableTrains = new ArrayList<>();
		Map<Integer, List<Train>> tickets = new HashMap<>();
	// check direct train with 0 connections
		if (fromStation.compareTo(toStation) < 0){
		if((trains.getFromStation().compareTo(fromStation) <= 0) && (trains.getToStation().compareTo(toStation) >= 0) && trains.getTrainNumber().contains("SB")){
			
			Train searched = new Train(trains.getTrainNumber(), trains.getTrainType(), trains.getDepartureTime(), trains.getArrivalTime(), fromStation, toStation);
			       availableTrains.add(searched);
			      // tickets.put(count++, availableTrains);
			}
		}
		else if(fromStation.compareTo(toStation) > 0){
			if((trains.getFromStation().compareTo(fromStation) >= 0) && (trains.getToStation().compareTo(toStation) <= 0) && trains.getTrainNumber().contains("NB")){
				
				Train searched = new Train(trains.getTrainNumber(), trains.getTrainType(), trains.getDepartureTime(), trains.getArrivalTime(), fromStation, toStation);
				       availableTrains.add(searched);
				      // tickets.put(count++, availableTrains);
				}
		}
		return availableTrains;
   }
   
   
   
   public static Map<Integer, List<Train>> checkFromToConnections(List<Train> trains,String email,Date departureDate,String departureTime, String fromStation, String toStation,int noOfConnections,int noOfTickets, String ticketType, String trainType){
	  
	   
		List<Train> availableTrains = new ArrayList<>();
		
		String originalFromStation = fromStation;
		
		Map<Integer, List<Train>> tickets = new HashMap<>();
		
	    
	    
	    int count =1;
		Boolean any = false;
		int connectionCount =0;
		
	    for(int i=0; i<trains.size() && tickets.size() < 5; i++){
	    	
	    	
	    /*	System.out.println("Train ID"+trains.get(i).getId());
	    	System.out.println("Arrival Time"+trains.get(i).getDepartureTime());
	    	System.out.println("Arrival Time"+trains.get(i).getArrivalTime());
	    	System.out.println("Capacity"+trains.get(i).getCapacity());
	    	System.out.println("From Station"+trains.get(i).getFromStation());
	    	System.out.println("To Station"+trains.get(i).getToStation());
	    	System.out.println("Train Type"+trains.get(i).getTrainType());
	    	System.out.println("Train Number"+trains.get(i).getTrainNumber());
	    	System.out.println("dep date"+trains.get(i).getDepartureDate());
	    	
	    	System.out.println("Here is type of train : "+trainType+"database value: "+trains.get(i).getTrainType());
	    */	
	    	
	    	
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    	LocalDate localDate = LocalDate.now();
	    	LocalDate nextweek = localDate.plusWeeks(1);
	    	
	    	int atime = Integer.parseInt(departureTime);
	    	atime = atime+100;
	    	
	    	int getTime = Integer.parseInt(trains.get(i).getDepartureTime());
	    	
	        // Check the capacity and departure time greater than 1 hour from now and date 
	    	if (trains.get(i).getCapacity() >= noOfTickets && (atime <= getTime) || ( localDate.equals(trains.get(i).getDepartureDate())   ) ){
	    		
	    			// check train type = Express or Regular 
	    			if (trains.get(i).getTrainType().equals(trainType) ){
	    				
	    				if (noOfConnections == 0){
	    				
	    					availableTrains = checkFromTo(trains.get(i), fromStation, toStation);
	    						if (!availableTrains.isEmpty()){
	    							tickets.put(count++, availableTrains);
	    							any = true;
	    							availableTrains = new ArrayList<>();
	    						}
	    				}
	    				else if (noOfConnections >= 1 && connectionCount < 3){
	    				
	    				//tickets = checkFromToConnections(trains.get(i), fromStation, toStation);
	    					System.out.println("Inside Connection 1 database ->"+trains.get(i).getFromStation()+"/ user-> "+fromStation );
	    				
	    					if (fromStation.compareTo(toStation) < 0 && trains.get(i).getTrainNumber().contains("SB")){
	    					
	    					if(trains.get(i).getFromStation().compareTo(fromStation) <= 0){
	    					    
	    						String tempToStation = "";
	    						if(trains.get(i).getToStation().compareTo(toStation) >= 0){
	    							tempToStation = toStation;
	    						}else{
	    							tempToStation = trains.get(i).getToStation();
	    						}
	    						
	    						Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
	    						availableTrains.add(searched);
	    					    connectionCount++;
	    						
	    						String tempfromStation = fromStation;
	    						fromStation = trains.get(i).getToStation();
	    						String arrives = trains.get(i).getArrivalTime();
	    				
	    						if ((trains.get(i).getToStation().compareTo(toStation) >= 0)){
	    							if (!availableTrains.isEmpty()){
	    									tickets.put(count++, availableTrains);
	    									availableTrains = new ArrayList<>();
	    									System.out.println("Adding ticket : "+trains.get(i).getTrainNumber()+ " Count-> "+count );
	    									any = true;
	    									connectionCount = 0;
	    							}
	    				    	 fromStation = originalFromStation;
	    				     }       
	    				   }
	    					
	    		    	}else if(fromStation.compareTo(toStation) > 0 && trains.get(i).getTrainNumber().contains("NB")){
	    		    			if(trains.get(i).getFromStation().compareTo(fromStation) >= 0){
	    					    
	    						String tempToStation = "";
	    						if(trains.get(i).getToStation().compareTo(toStation) <= 0){
	    							tempToStation = toStation;
	    						}else{
	    							tempToStation = trains.get(i).getToStation();
	    						}
	    						
	    						Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
	    						availableTrains.add(searched);
	    					    connectionCount++;
	    						
	    						String tempfromStation = fromStation;
	    						fromStation = trains.get(i).getToStation();
	    						String arrives = trains.get(i).getArrivalTime();
	    				
	    						if ((trains.get(i).getToStation().compareTo(toStation) <= 0)){
	    							if (!availableTrains.isEmpty()){
	    									tickets.put(count++, availableTrains);
	    									availableTrains = new ArrayList<>();
	    									System.out.println("Adding ticket : "+trains.get(i).getTrainNumber()+ " Count-> "+count );
	    									any = true;
	    									connectionCount = 0;
	    							}
	    				    	 fromStation = originalFromStation;
	    				     }       
	    				   }
	    		    	}// if if (noOfConnections >= 1 && connectionCount < 3)-------------------ends here
	    					
	    			}
	    			
	    		}
	    		else if( trainType.equals("A") || trainType.equals("E")){
                  if (trainType.equals("A")){
               	   any = true;
                  }
	    			if (trains.get(i).getTrainType().equals("E") || any){
   				
   				if (noOfConnections == 0){
   				
   					availableTrains = checkFromTo(trains.get(i), fromStation, toStation);
	    				if (!availableTrains.isEmpty()){
	    			        tickets.put(count++, availableTrains);
	    			        availableTrains = new ArrayList<>();
	    			        any = true;
	    				}
   				}
   				else if (noOfConnections >= 1 && connectionCount < 3){
   				
   				//tickets = checkFromToConnections(trains.get(i), fromStation, toStation);
   					if (fromStation.compareTo(toStation) < 0 && trains.get(i).getTrainNumber().contains("SB")){
   					if(trains.get(i).getFromStation().compareTo(fromStation) <= 0){
   						
   						String tempToStation = "";
   						if(trains.get(i).getToStation().compareTo(toStation) >= 0){
   							tempToStation = toStation;
   						}else{
   							tempToStation = trains.get(i).getToStation();
   						}
   					
   						Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
   						availableTrains.add(searched);
   						connectionCount++;
   					
   						String tempfromStation = fromStation;
   						fromStation = trains.get(i).getToStation();
   						String arrives = trains.get(i).getArrivalTime();
   				
   						if ((trains.get(i).getToStation().compareTo(toStation) >= 0)){
   				    	
   							if (!availableTrains.isEmpty()){
									tickets.put(count++, availableTrains);
									availableTrains = new ArrayList<>();
									any = true;
									connectionCount = 0;
   							}
   				    	 
   				    	 fromStation = originalFromStation;
   				     }       
   				}
   				}else if(fromStation.compareTo(toStation) > 0 && trains.get(i).getTrainNumber().contains("NB")){
	    			if(trains.get(i).getFromStation().compareTo(fromStation) >= 0){
					    
					String tempToStation = "";
					if(trains.get(i).getToStation().compareTo(toStation) <= 0){
						tempToStation = toStation;
					}else{
						tempToStation = trains.get(i).getToStation();
					}
					
					Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
					availableTrains.add(searched);
				    connectionCount++;
					
					String tempfromStation = fromStation;
					fromStation = trains.get(i).getToStation();
					String arrives = trains.get(i).getArrivalTime();
			
					if ((trains.get(i).getToStation().compareTo(toStation) <= 0)){
						if (!availableTrains.isEmpty()){
								tickets.put(count++, availableTrains);
								availableTrains = new ArrayList<>();
								System.out.println("Adding ticket : "+trains.get(i).getTrainNumber()+ " Count-> "+count );
								any = true;
								connectionCount = 0;
						}
			    	 fromStation = originalFromStation;
			     }       
			   }
	    	}
   				
   					
   			  }
	    		}
   		
	    		}
	    	}
	    }
	   
		return tickets;
   }
   

   
  /* 
   
   public static Map<Integer, List<Train>> checkRoundTrip(List<Train> trains,String email,Date departureDate,String departureTime, String fromStation, String toStation,int noOfConnections,int noOfTickets, String ticketType, String trainType){
	  
	   
		List<Train> availableTrains = new ArrayList<>();
		
		String originalFromStation = fromStation;
		
		Map<Integer, List<Train>> tickets = new HashMap<>();
		
	    
	    
	    int count =1;
		Boolean any = false;
		int connectionCount =0;
		
	    for(int i=0; i<trains.size() && tickets.size() < 5; i++){
	    	
	    	
	    	System.out.println("Train ID"+trains.get(i).getId());
	    	System.out.println("Arrival Time"+trains.get(i).getDepartureTime());
	    	System.out.println("Arrival Time"+trains.get(i).getArrivalTime());
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
	    	
	    	int getTime = Integer.parseInt(trains.get(i).getDepartureTime());
	    	
	        // Check the capacity and departure time greater than 1 hour from now and date 
	    	if (trains.get(i).getCapacity() >= noOfTickets && (atime <= getTime) || ( localDate.equals(trains.get(i).getDepartureDate())   ) ){
	    		
	    			// check train type = Express or Regular 
	    			if (trains.get(i).getTrainType().equals(trainType) ){
	    				
	    				if (noOfConnections == 0){
	    				
	    					availableTrains = checkFromTo(trains.get(i), fromStation, toStation);
	    						if (!availableTrains.isEmpty()){
	    							tickets.put(count++, availableTrains);
	    							any = true;
	    							availableTrains = new ArrayList<>();
	    						}
	    				}
	    				else if (noOfConnections >= 1 && connectionCount < 3){
	    				
	    				//tickets = checkFromToConnections(trains.get(i), fromStation, toStation);
	    					System.out.println("Inside Connection 1 database ->"+trains.get(i).getFromStation()+"/ user-> "+fromStation );
	    				
	    					if(trains.get(i).getFromStation().compareTo(fromStation) <= 0){
	    					    
	    						String tempToStation = "";
	    						if(trains.get(i).getToStation().compareTo(toStation) >= 0){
	    							tempToStation = toStation;
	    						}else{
	    							tempToStation = trains.get(i).getToStation();
	    						}
	    						
	    						Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
	    						availableTrains.add(searched);
	    					    connectionCount++;
	    						
	    						String tempfromStation = fromStation;
	    						fromStation = trains.get(i).getToStation();
	    						String arrives = trains.get(i).getArrivalTime();
	    				
	    						if ((trains.get(i).getToStation().compareTo(toStation) >= 0)){
	    							if (!availableTrains.isEmpty()){
	    									tickets.put(count++, availableTrains);
	    									availableTrains = new ArrayList<>();
	    									System.out.println("Adding ticket : "+trains.get(i).getTrainNumber()+ " Count-> "+count );
	    									any = true;
	    									connectionCount = 0;
	    							}
	    				    	 fromStation = originalFromStation;
	    				     }       
	    				}
	    			}
	    			
	    		}
	    		else if( trainType.equals("A") || trainType.equals("E")){
                  if (trainType.equals("A")){
               	   any = true;
                  }
	    			if (trains.get(i).getTrainType().equals("E") || any){
   				
   				if (noOfConnections == 0){
   				
   					availableTrains = checkFromTo(trains.get(i), fromStation, toStation);
	    				if (!availableTrains.isEmpty()){
	    			        tickets.put(count++, availableTrains);
	    			        availableTrains = new ArrayList<>();
	    			        any = true;
	    				}
   				}
   				else if (noOfConnections >= 1 && connectionCount < 3){
   				
   				//tickets = checkFromToConnections(trains.get(i), fromStation, toStation);
   				
   					if(trains.get(i).getFromStation().compareTo(fromStation) <= 0){
   						
   						String tempToStation = "";
   						if(trains.get(i).getToStation().compareTo(toStation) >= 0){
   							tempToStation = toStation;
   						}else{
   							tempToStation = trains.get(i).getToStation();
   						}
   					
   						Train searched = new Train(trains.get(i).getTrainNumber(), trains.get(i).getTrainType(), trains.get(i).getDepartureTime(), trains.get(i).getArrivalTime(), fromStation, tempToStation);
   						availableTrains.add(searched);
   						connectionCount++;
   					
   						String tempfromStation = fromStation;
   						fromStation = trains.get(i).getToStation();
   						String arrives = trains.get(i).getArrivalTime();
   				
   						if ((trains.get(i).getToStation().compareTo(toStation) >= 0)){
   				    	
   							if (!availableTrains.isEmpty()){
									tickets.put(count++, availableTrains);
									availableTrains = new ArrayList<>();
									any = true;
									connectionCount = 0;
   							}
   				    	 
   				    	 fromStation = originalFromStation;
   				     }       
   				}
   			  }
	    		}
   		
	    		}
	    	}
	    }
	   
		return tickets;
   }
   
   */
   
   
}