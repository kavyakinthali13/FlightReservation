package com.example.FlightReservation.exceptions;

public class BookingDetailsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public BookingDetailsNotFoundException(String message) {
		super(message);
	}

}
