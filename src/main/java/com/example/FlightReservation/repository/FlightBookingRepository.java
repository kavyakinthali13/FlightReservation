package com.example.FlightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FlightReservation.model.Ticket;
@Repository
public interface FlightBookingRepository extends JpaRepository<Ticket, Long> {

}
