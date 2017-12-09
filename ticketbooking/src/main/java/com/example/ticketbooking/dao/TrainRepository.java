package com.example.ticketbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticketbooking.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{

}
