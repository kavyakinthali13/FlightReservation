package com.example.FlightReservation.exceptions;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFoundException(FlightNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", " filghts are not availble");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TicketsNotFoundException.class)
	public ResponseEntity<Object> handleTicketsNotFoundException(TicketsNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", " please enter the no of tickets");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TicketIdNotFoundException.class)

	public ResponseEntity<Object> handleBusIdNotFoundException(TicketIdNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("message", " tickets with ticket id not available");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(BusIdNotFoundException.class)
	public ResponseEntity<Object> handleBusIdNotFoundException(BusIdNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", " buses not are availble");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalance.class)
	public ResponseEntity<Object> handleInsufficientBalance(InsufficientBalance ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "entered amount is greater than the available balance");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccountDetailsNotFoundException.class)
	public ResponseEntity<Object> handleAccountDetailsNotFoundException(AccountDetailsNotFoundException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "please enter your account number ");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SeatsNotAvailbleException.class)
	public ResponseEntity<Object> handleSeatsNotAvailbleException(SeatsNotAvailbleException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "please enter no of tickets less than or equal to the NoOfSeatsAvailable ");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookingDetailsNotFoundException.class)
	public ResponseEntity<Object> handleBookingDetailsNotFoundException(BookingDetailsNotFoundException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "missing passengerId or flightId");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
