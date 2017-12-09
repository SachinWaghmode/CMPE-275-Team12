package com.example.ticketbooking.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="train")
public class Train {
	
	public Train(Long id, String trainNumber, String trainType, Date departureDate, String journeyTime,
			String arrivalTime, String fromStation, String toStation, int capacity) {
		super();
		Id = id;
		this.trainNumber = trainNumber;
		this.trainType = trainType;
		this.departureDate = departureDate;
		this.journeyTime = journeyTime;
		this.arrivalTime = arrivalTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.capacity = capacity;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotBlank
	private String trainNumber;
	@NotBlank
	private String trainType;
	@NotBlank
	private Date departureDate;
	@NotBlank
	private String journeyTime;
	@NotBlank
	private String arrivalTime;
	@NotBlank
	private String fromStation;
	@NotBlank
	private String toStation;
	@NotBlank
	private int capacity;
	
	public Train(){
		
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	
	

}
