package com.example.colombo_life.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.colombo_life.model.Passenger;
import com.example.colombo_life.repository.PassengerRepository;
import com.example.colombo_life.repository.ReservationRepository;



@RestController
public class PassengerController {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@PostMapping("/reservation/{reservation_id}/passenger")
	public void bookingFlight(@PathVariable String reservation_id,@RequestBody Passenger passenger) {
		  	reservationRepository.findById(reservation_id).map(reservation -> {                //get the Reservation object which reservation_id == given id, And to get it use many table mapping attribute 
		 		  	passenger.setReservation(reservation);            // Inject Reservation object to Passenger Object
		 			return passengerRepository.save(passenger);       // Save the passenger object
		 		});	
	}
	@DeleteMapping("/reservation/{reservation_id}/passenger/{passportNumber}")
	public void  deletePassenger(@PathVariable String passportNumber,@PathVariable String reservation_id) {
		passengerRepository.findById(passportNumber).map(passenger -> {
			passengerRepository.delete(passenger);
			return ResponseEntity.ok().build();
		});
	}
	@PutMapping("/reservation/{reservation_id}/passenger/{passportNumber}")
	public Optional<Object> updatePassenger(@PathVariable String passportNumber,@RequestBody Passenger passengerRequest) {
		 return passengerRepository.findById(passportNumber).map(passenger -> {
			passenger.setAddress(passengerRequest.getAddress());
			passenger.setContractNo(passengerRequest.getContractNo());
			passenger.setName(passengerRequest.getName());
			return passengerRepository.save(passenger);
		});
	}
}











