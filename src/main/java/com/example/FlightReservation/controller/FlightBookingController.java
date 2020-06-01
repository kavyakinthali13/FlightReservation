package com.example.FlightReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FlightReservation.dto.ResponseDto;
import com.example.FlightReservation.dto.TicketDto;
import com.example.FlightReservation.exceptions.AccountDetailsNotFoundException;
import com.example.FlightReservation.exceptions.BookingDetailsNotFoundException;
import com.example.FlightReservation.exceptions.FlightNotFoundException;
import com.example.FlightReservation.exceptions.InsufficientBalance;
import com.example.FlightReservation.exceptions.SeatsNotAvailbleException;
import com.example.FlightReservation.exceptions.TicketsNotFoundException;
import com.example.FlightReservation.service.FlightBookingService;

@RestController
public class FlightBookingController {
	@Autowired
	private FlightBookingService flightBookingService;
	
	
	@PostMapping("/bookTicket")
	public ResponseEntity<ResponseDto> ticketBooking(@RequestBody TicketDto ticketDto) throws SeatsNotAvailbleException, BookingDetailsNotFoundException, AccountDetailsNotFoundException, InsufficientBalance, FlightNotFoundException, TicketsNotFoundException  {
		ResponseDto ticketResponseDto=new ResponseDto();
		flightBookingService.bookTicket(ticketDto);
		ticketResponseDto.setMessage("tickets booked successfully");
		ticketResponseDto.getMessage();
		return new ResponseEntity<ResponseDto>(ticketResponseDto, HttpStatus.OK);	
	}

}
