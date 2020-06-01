package com.example.FlightReservation.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import com.example.FlightReservation.dto.ResponseDto;
import com.example.FlightReservation.dto.TicketDto;
import com.example.FlightReservation.exceptions.AccountDetailsNotFoundException;
import com.example.FlightReservation.exceptions.BookingDetailsNotFoundException;
import com.example.FlightReservation.exceptions.FlightNotFoundException;
import com.example.FlightReservation.exceptions.InsufficientBalance;
import com.example.FlightReservation.exceptions.SeatsNotAvailbleException;
import com.example.FlightReservation.exceptions.TicketsNotFoundException;
import com.example.FlightReservation.model.Flight;
import com.example.FlightReservation.model.Passenger;
import com.example.FlightReservation.model.Ticket;
import com.example.FlightReservation.service.FlightBookingService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightBookingControllerTest {

	@InjectMocks
	FlightBookingController flightBookingController;

	@Mock
	FlightBookingService flightBookingService;

	Ticket ticket = new Ticket();
	TicketDto ticketDto = new TicketDto();
	Passenger passenger = new Passenger();
	Flight flight = new Flight();
	Date date = new Date(12 - 2 - 2020);

	@Test
	public void testSaveTicketForPositive() throws BookingDetailsNotFoundException, SeatsNotAvailbleException,
			AccountDetailsNotFoundException, InsufficientBalance, FlightNotFoundException, TicketsNotFoundException {
		ticket.setTicketId(1l);
		ticket.setReservationDate(date);
		passenger.setPassengerId(1l);
		passenger.setFirstname("sai");
		passenger.setLastname("kumar");
		passenger.setEmail("sai@gmail.com");
		passenger.setPassword("123sai");
		passenger.setPhoneNumber("9666168535");
		flight.setAvailableTickets(12);
		flight.setDepartureDate(date);
		flight.setArrivalDate(date);
		flight.setDestination("chennai");
		flight.setSource("tpt");
		flight.setFlightName("indigoo");
		flight.setFlightNumber(1l);
		flight.setFlightFare(1200l);
		ticketDto.setTicketId(1l);
		ticketDto.setFlight(flight);
		ticketDto.setPassenger(passenger);
		ticketDto.setNumberOfTickets(8);
		Mockito.when(flightBookingService.bookTicket(ticketDto)).thenReturn((ticket));
		ResponseEntity<ResponseDto> resTicket = flightBookingController.ticketBooking(ticketDto);
	}

	@Test
	public void testSaveTicketForNegitve() throws BookingDetailsNotFoundException, SeatsNotAvailbleException,
			AccountDetailsNotFoundException, InsufficientBalance, FlightNotFoundException, TicketsNotFoundException {
		ticket.setTicketId(1l);
		ticket.setReservationDate(date);
		passenger.setPassengerId(1l);
		passenger.setFirstname("sai");
		passenger.setLastname("kumar");
		passenger.setEmail("sai@gmail.com");
		passenger.setPassword("123sai");
		passenger.setPhoneNumber("9666168535");
		flight.setAvailableTickets(12);
		flight.setDepartureDate(date);
		flight.setArrivalDate(date);
		flight.setDestination("chennai");
		flight.setSource("tpt");
		flight.setFlightName("indigoo");
		flight.setFlightNumber(1l);
		flight.setFlightFare(1200l);
		ticketDto.setTicketId(1l);
		ticketDto.setFlight(flight);
		ticketDto.setPassenger(passenger);
		ticketDto.setNumberOfTickets(8);
		Mockito.when(flightBookingService.bookTicket(Mockito.any(TicketDto.class))).thenReturn((ticket));
		ResponseEntity<ResponseDto> resTicket = flightBookingController.ticketBooking(ticketDto);
	}


	@Test(expected=NullPointerException.class)
	public void testticketBookingForException() throws BookingDetailsNotFoundException, SeatsNotAvailbleException, AccountDetailsNotFoundException, InsufficientBalance, FlightNotFoundException, TicketsNotFoundException{
		Mockito.when(flightBookingService.bookTicket(Mockito.any(TicketDto.class))).thenThrow(NullPointerException.class);
		ResponseEntity<ResponseDto> resTicket = flightBookingController.ticketBooking(ticketDto);

	}
}
		  
	