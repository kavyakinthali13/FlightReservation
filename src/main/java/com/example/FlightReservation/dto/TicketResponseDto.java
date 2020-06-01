package com.example.FlightReservation.dto;

import java.util.Date;

public class TicketResponseDto {

	private Long ticketId;

	private int numberOfTickets;

	private Date reservationDate;

	private long flightId;

	private long passengerId;

	private String source;

	private String destination;

	public Long getTicketId() {

		return ticketId;

	}

	public void setTicketId(Long ticketId) {

		this.ticketId = ticketId;

	}

	public int getNumberOfTickets() {

		return numberOfTickets;

	}

	public void setNumberOfTickets(int numberOfTickets) {

		this.numberOfTickets = numberOfTickets;

	}

	public Date getReservationDate() {

		return reservationDate;

	}

	public void setReservationDate(Date reservationDate) {

		this.reservationDate = reservationDate;

	}

	public long getFlightId() {

		return flightId;

	}

	public void setFlightId(long flightId) {

		this.flightId = flightId;

	}

	public long getPassengerId() {

		return passengerId;

	}

	public void setPassengerId(long passengerId) {

		this.passengerId = passengerId;

	}

	public String getSource() {

		return source;

	}

	public void setSource(String source) {

		this.source = source;

	}

	public String getDestination() {

		return destination;

	}

	public void setDestination(String destination) {

		this.destination = destination;

	}

}
