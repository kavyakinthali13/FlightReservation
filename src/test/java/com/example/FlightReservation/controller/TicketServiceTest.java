package com.example.FlightReservation.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.FlightReservation.dto.TicketResponseDto;
import com.example.FlightReservation.exceptions.TicketIdNotFoundException;
import com.example.FlightReservation.model.Flight;
import com.example.FlightReservation.model.Passenger;
import com.example.FlightReservation.model.Ticket;
import com.example.FlightReservation.repository.FlightRepository;
import com.example.FlightReservation.repository.TicketsRepository;
import com.example.FlightReservation.service.TicketService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)

public class TicketServiceTest {

	@InjectMocks

	TicketService ticketService;

	@Mock

	TicketsRepository ticketsRepository;

	@Mock

	FlightRepository flightRepository;

	@SuppressWarnings("deprecation")

	@Test(expected = TicketIdNotFoundException.class)

	public void testFindByTicketId() throws TicketIdNotFoundException {
		Date date = new Date(22 - 2 - 2020);

		Passenger passenger = new Passenger();

		passenger.setEmail("gs@gmail.com");

		passenger.setFirstname("gs");

		passenger.setLastname("chin1");

		passenger.setPassengerId(200L);

		passenger.setPassword("hai");

		passenger.setPhoneNumber("9876543210");

		Flight flight = new Flight();

		flight.setAvailableTickets(60);

		flight.setDestination("pune");

		flight.setFlightNumber(800L);

		flight.setSource("sklm");

		flightRepository.save(flight);

		Ticket tickets = new Ticket();

		tickets.setTicketId(100L);

		tickets.setReservationDate(date);

		tickets.setNumberOfTickets(40);

		tickets.setFlight(flight);

		tickets.setPassenger(passenger);

		ticketsRepository.save(tickets);

		tickets = ticketsRepository.findById(3000L)
				.orElseThrow(() -> new TicketIdNotFoundException("ticket with id not found"));
		/* Throw(new TicketIdNotFoundException("ticket with id not found")) */;

		TicketResponseDto ticketDto = new TicketResponseDto();

		ticketDto.setDestination("pune");

		ticketDto.setFlightId(800L);

		ticketDto.setNumberOfTickets(40);

		ticketDto.setPassengerId(200L);

		ticketDto.setReservationDate(date);

		ticketDto.setSource("sklm");

		ticketDto.setTicketId(100L);

		Assert.assertEquals(tickets.getTicketId(), ticketDto.getTicketId());

	}

}