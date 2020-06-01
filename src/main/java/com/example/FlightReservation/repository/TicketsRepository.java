package com.example.FlightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlightReservation.model.Ticket;

public interface TicketsRepository extends JpaRepository<Ticket, Long>{

}
