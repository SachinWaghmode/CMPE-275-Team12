package com.example.ticketbooking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ticketbooking.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{
/*
	@Query("Select * from Train t orderby t.arrivalTime ")
	List<Train> findorderedTrain();
	*/
}
