package com.example.ticketbooking.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Entity
@Table(name="train")
public class Train {
	
	@Id
	private String trainNumber;
	@NotBlank
	private String trainType;
	
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

	public Train(String trainNumber, String trainType) {
		super();
		this.trainNumber = trainNumber;
		this.trainType = trainType;
	}
	

}
