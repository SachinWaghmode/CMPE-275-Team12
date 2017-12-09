package com.example.ticketbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticketbooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
