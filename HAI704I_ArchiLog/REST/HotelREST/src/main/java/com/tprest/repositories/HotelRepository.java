package com.tprest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tprest.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>
{
	
}