package com.example.colombo_life.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.colombo_life.model.Flight;
import com.example.colombo_life.repository.FlightRepository;



@RestController
public class FlightController {
	@Autowired
	FlightRepository flightRepository;
	
	@GetMapping("/flight")
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
	@PostMapping("/flight")
	public Flight createNote(@RequestBody Flight flight) {
	    return flightRepository.save(flight);
	}
	//Update Not Working
	@PutMapping("/flight/{flight_id}")
	public void updateFlight(@PathVariable String flight_id,@RequestBody Flight flighRequest) {
		flightRepository.findById(flight_id).map(flight -> {
		    	flight.setNoOfPassengers(flighRequest.getNoOfPassengers());
			  return flightRepository.save(flight);
		});
	}
	
	@DeleteMapping("/flight/{flight_id}")
	public void  deleteFlight(@PathVariable String flight_id) {
			flightRepository.findById(flight_id).map(flight -> {
				flightRepository.delete(flight);
			return ResponseEntity.ok().build();
		});
	}
}
