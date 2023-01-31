package com.tprest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tprest.exceptions.HotelNotFoundException;
import com.tprest.models.Hotel;
import com.tprest.repositories.HotelRepository;

@RestController
public class HotelController
{
	/* ATTRIBUTES */
	@Autowired
	private HotelRepository repository;
	private static final String uri = "hotelservice/api";
	
	/* METHODS */
	@GetMapping(uri+"/hotels")
	public List<Hotel> getAllHotels()
	{
		return repository.findAll();
	}
	
	@GetMapping(uri+"/hotels/count")
	public String count()
	{
		return String.format("{\"%s\": %d}", "count", repository.count());
	}
	
	@GetMapping(uri+"/hotels/{id}")
	public Hotel getHotelById(@PathVariable long id) throws HotelNotFoundException
	{
		return repository.findById(id)
		.orElseThrow(() -> new HotelNotFoundException("Error: could not find hotel with ID " + id));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(uri+"/hotels")
	public Hotel createHotel(@RequestBody Hotel hotel)
	{
		return repository.save(hotel);
	}
	
	@PutMapping(uri+"/hotel/{id}")
	public Hotel updateHotel(@RequestBody Hotel newHotel, @PathVariable long id)
	{
		return repository.findById(id)
				
			.map(hotel -> {
			hotel.setNom(newHotel.getNom());
			hotel.setAdresse(newHotel.getAdresse());
			hotel.setNbEtoiles(newHotel.getNbEtoiles());
			return repository.save(hotel);
			})
			.orElseGet(() -> repository.save(newHotel));
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(uri+"/hotels/{id}")
	public void deleteEmployee(@PathVariable long id) throws HotelNotFoundException
	{
		Hotel hotel = repository.findById(id)
				
		.orElseThrow(() -> new HotelNotFoundException(
		"Error: could not find employee with ID " + id));
		repository.delete(hotel);
	}
}
