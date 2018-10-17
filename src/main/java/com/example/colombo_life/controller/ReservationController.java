package com.example.colombo_life.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.colombo_life.model.Passenger;
import com.example.colombo_life.model.Reservation;
import com.example.colombo_life.repository.FlightRepository;
import com.example.colombo_life.repository.PassengerRepository;
import com.example.colombo_life.repository.ReservationRepository;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@GetMapping("/reservation")
	public List<Reservation> getAllFlightsShedules() {
		return reservationRepository.findAll();
	}
	@PostMapping("/flight/{flight_id}/reservation")
	public void createFlightShedule(@PathVariable String flight_id,@RequestBody Reservation reservation) {
		flightRepository.findById(flight_id).map(flight -> { // get the Flight object which flight_id == given flight_id, Then mention mapping in foreign key table 
			reservation.setFlight(flight); // Inject the object to reservation object
			return reservationRepository.save(reservation); // Save the reservation object
		});
	}
}
