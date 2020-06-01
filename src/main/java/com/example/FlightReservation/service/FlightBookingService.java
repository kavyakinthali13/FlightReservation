package com.example.FlightReservation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.FlightReservation.dto.TicketDto;
import com.example.FlightReservation.exceptions.AccountDetailsNotFoundException;
import com.example.FlightReservation.exceptions.BookingDetailsNotFoundException;
import com.example.FlightReservation.exceptions.FlightNotFoundException;
import com.example.FlightReservation.exceptions.InsufficientBalance;
import com.example.FlightReservation.exceptions.SeatsNotAvailbleException;
import com.example.FlightReservation.exceptions.TicketsNotFoundException;
import com.example.FlightReservation.model.Flight;
import com.example.FlightReservation.model.Ticket;
import com.example.FlightReservation.repository.FlightBookingRepository;
import com.example.FlightReservation.repository.FlightRepository;

@Service
public class FlightBookingService {
	@Autowired
	private FlightBookingRepository flightBookingRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	RestTemplate restTemplate;
@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

  Flight flight = new Flight();

	public Ticket bookTicket(TicketDto ticketDto) throws SeatsNotAvailbleException, BookingDetailsNotFoundException, AccountDetailsNotFoundException, InsufficientBalance, FlightNotFoundException, TicketsNotFoundException  {
		if ( ticketDto.getPassenger().getPassengerId() != 0 &&  ticketDto.getFlight().getFlightNumber() != 0) {
			Ticket ticket = new Ticket();
			BeanUtils.copyProperties(ticketDto, ticket);
			try {
				flight= flightRepository.findById(ticket.getFlight().getFlightNumber()).get();

			}
			catch(Exception e) {
				throw new FlightNotFoundException("filgt not found");
			}
			if(ticketDto.getNumberOfTickets()!=0)
			{
				if (flight.getAvailableTickets() > ticketDto.getNumberOfTickets()) {
					flight.setAvailableTickets(flight.getAvailableTickets() - ticketDto.getNumberOfTickets());
					paymentForFlightTicket(ticketDto,flight);
					flightRepository.save(flight);
					return flightBookingRepository.save(ticket);
				} else {
					throw new SeatsNotAvailbleException("please enter no of tickets less than or equal to the NoOfSeatsAvailable ");
				}

			}
			else {
				throw new TicketsNotFoundException("no of tickets should not be empty");
			}
			
		} else {
			throw new BookingDetailsNotFoundException("Missing flight number or  pssenger Id");
		}

	}

	public String paymentForFlightTicket(TicketDto ticketDto, Flight flight) throws AccountDetailsNotFoundException, InsufficientBalance {
		if (ticketDto.getAccountNumber() != 0 && ticketDto.getBeneficiaryAccountNumber() != 0) {
			int amount = (int) (ticketDto.getNumberOfTickets() * flight.getFlightFare());
			String amount1 = Integer.toString(amount);
			String url = "http://BANK-SERVICE/transaction";
			String beneficiaryAccountNumber = "" + ticketDto.getBeneficiaryAccountNumber();
			String accountNumber = "" + ticketDto.getAccountNumber();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			Map<String, String> params = new HashMap<String, String>();
			params.put("accountNumber", accountNumber);
			params.put("beneficiaryAccountNumber", beneficiaryAccountNumber);
			params.put("amount", amount1);
			params.put("customerId", "1");

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
			try {

			String result = restTemplate.getForObject(builder.toUriString(), String.class);
			return result;
			}
			catch(Exception e) {
				throw new InsufficientBalance("entered amount is greater than the availble balance");
				
			}
		} else {
			throw new AccountDetailsNotFoundException("enter account correct number");
		}

	}

}
