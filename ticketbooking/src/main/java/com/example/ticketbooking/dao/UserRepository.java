package com.example.ticketbooking.dao;
import org.springframework.data.jpa.repository.*;

import com.example.ticketbooking.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
