package com.example.ticketbooking.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date departureDate;
	@NotBlank
	private String departureTime;
	@NotBlank
	private String arrivalTime;
	@NotBlank
	private String fromStation;
	@NotBlank
	private String toStation;
	
	private String returnDepartureTime;

	private String returnArrivalTime;

	
	private int price;
	
	private int noOfConnections;
	
	private int noOfTickets;
	
	private boolean roundTrip;
	
	@ManyToMany(mappedBy="bookings")
	private List<Train> trains;
	


	
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
	public int getPrice() {
		return price;
	}
	
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getReturnDepartureTime() {
		return returnDepartureTime;
	}
	public void setReturnDepartureTime(String returnDepartureTime) {
		this.returnDepartureTime = returnDepartureTime;
	}
	public String getReturnArrivalTime() {
		return returnArrivalTime;
	}
	public void setReturnArrivalTime(String returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
	}
	public int getNoOfConnections() {
		return noOfConnections;
	}
	public void setNoOfConnections(int noOfConnections) {
		this.noOfConnections = noOfConnections;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public boolean isRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Booking(Long id, User user, String departureTime, String fromStation, String toStation, int price) {
		super();
		this.id = id;
		this.user = user;
		this.departureTime = departureTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.price = price;
	}
	public Booking(User user,Date departureDate, String departureTime, String arrivalTime, String fromStation,
			String toStation, int noOfConnections, int noOfTickets) {
		super();
		this.user = user;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.noOfConnections = noOfConnections;
		this.noOfTickets = noOfTickets;
		
		// TODO Auto-generated constructor stub
	}
	public List<Train> getTrains() {
		return trains;
	}
	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
	public Booking(Long id, Date departureDate, String departureTime, String arrivalTime, String fromStation,
			String toStation, String returnDepartureTime, String returnArrivalTime, int price, int noOfConnections,
			int noOfTickets, boolean roundTrip, List<Train> trains, User user) {
		super();
		this.id = id;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.returnDepartureTime = returnDepartureTime;
		this.returnArrivalTime = returnArrivalTime;
		this.price = price;
		this.noOfConnections = noOfConnections;
		this.noOfTickets = noOfTickets;
		this.roundTrip = roundTrip;
		this.trains = trains;
		this.user = user;
	}
	
	
	

}
