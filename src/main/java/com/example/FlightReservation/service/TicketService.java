package com.example.FlightReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightReservation.dto.TicketResponseDto;
import com.example.FlightReservation.exceptions.TicketIdNotFoundException;
import com.example.FlightReservation.model.Ticket;
import com.example.FlightReservation.repository.TicketsRepository;

@Service

public class TicketService {

 @Autowired

 TicketsRepository ticketsRepository;

 


 public TicketResponseDto findByTicketId(long id) throws TicketIdNotFoundException {

 Ticket tickets = new Ticket();

 tickets=ticketsRepository.findById(id).orElseThrow(()->new TicketIdNotFoundException("ticket with id not found"));

 TicketResponseDto ticketDto = new TicketResponseDto();

 ticketDto.setDestination(tickets.getFlight().getDestination());

 ticketDto.setFlightId(tickets.getFlight().getFlightNumber());

 ticketDto.setNumberOfTickets(tickets.getNumberOfTickets());

 ticketDto.setPassengerId(tickets.getPassenger().getPassengerId());

 ticketDto.setReservationDate(tickets.getReservationDate());

 ticketDto.setSource(tickets.getFlight().getSource());

 ticketDto.setTicketId(tickets.getTicketId());

 return ticketDto;

 }

}