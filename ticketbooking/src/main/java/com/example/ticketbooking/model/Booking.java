package com.example.ticketbooking.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String departureTime;
	@NotBlank
	private String fromStation;
	@NotBlank
	private String toStation;
	@NotBlank
	private Long price;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="trainNumber")
	private Train train;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="email")
	private User user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Booking(Long id, User user, String departureTime, String fromStation, String toStation, Long price) {
		super();
		this.id = id;
		this.user = user;
		this.departureTime = departureTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.price = price;
	}
	
	
	

}
