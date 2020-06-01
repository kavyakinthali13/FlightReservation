package com.example.FlightReservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightReservation.dto.FlightDto;
import com.example.FlightReservation.exceptions.FlightNotFoundException;
import com.example.FlightReservation.model.Flight;
import com.example.FlightReservation.repository.FlightRepository;

@Service

public class FlightService {

	@Autowired

	FlightRepository flightRepository;

	public List<Flight> findBySourceAndDestinationAndDepatureDate(FlightDto flightDto) throws FlightNotFoundException {

		List<Flight> flights = flightRepository.findFlightBySourceAndDestinationAndDepartureDate(flightDto.getSource(),

				flightDto.getDestination(), flightDto.getDepartureDate());

		if (flights.isEmpty()) {

			throw new FlightNotFoundException("filght not found");

		} else {

			return flights;

		}

	}

	public List<Flight> findBySourceAndDestination(String source, String destination) throws FlightNotFoundException {

		List<Flight> flights = flightRepository.findFlightBySourceAndDestination(source, destination);

		if (flights.isEmpty()) {

			throw new FlightNotFoundException("flight not found");

		} else {

			return flights;

		}

	}

	public List<Flight> findByName(String flightName) throws FlightNotFoundException {

		List<Flight> flights = flightRepository.findByFlightName(flightName);

		if (flights.isEmpty()) {

			throw new FlightNotFoundException("flight not found");

		} else {

			return flights;

		}

	}
}
