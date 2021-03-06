package com.example.demo.controller;

import java.util.List;

import com.example.demo.entities.ReservationId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




import com.example.demo.entities.HotelsDetails;
import com.example.demo.entities.Reservations;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.ReservationsRepo;



@RestController
public class RestAController {
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private ReservationsRepo reservationsRepo;
	
	@GetMapping(value = "/")
	public String sayhi() {
		return "Welcome";
	}
	
	@GetMapping(value = "/HotelDetails")
	public List<HotelsDetails> getHotelDetails() {
		return hotelRepo.findAll();
	}
	
	@PostMapping(value = "/reservation")
	public ReservationId saveReservation (@RequestBody Reservations reservation) {
		reservation.getGuests().forEach(guest -> guest.setReservation(reservation));
		reservationsRepo.save(reservation);
		ReservationId reservationId = new ReservationId();
		reservationId.setReservations(Long.toString(reservation.getReservation_id()));
		return reservationId;
		//return "Your Hotel is booked and " + Long.toString(reservation.getReservation_id()) + " is your reservation ID ";
	}
	
	//@PostMapping(value = "/res")
 //   public Reservations save(@RequestBody Reservations reservation) {
 //      System.out.println(reservation.toString());
//       reservation.getResvervation().forEach(stage -> stage.setReservation(reservation));
 //      return reservationsRepo.save(reservation);
 //   }
	
	
	
    
	
}
