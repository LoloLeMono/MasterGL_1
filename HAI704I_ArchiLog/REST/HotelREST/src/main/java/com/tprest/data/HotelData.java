package com.tprest.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tprest.models.Hotel;
import com.tprest.repositories.HotelRepository;

@Configuration
public class HotelData
{
	/* ATTRIBUTES */
	private Logger logger = LoggerFactory.getLogger(HotelData.class);
	
	@Bean
	public CommandLineRunner initDatabase(HotelRepository repository)
	{
		return args ->
		{
			logger.info("Preloading database with " + repository.save(
			new Hotel("Mercure", "3 rue kylian mbappe", 3)));
			logger.info("Preloading database with " + repository.save(
			new Hotel("Ibis", "5 boulevard zizou", 2)));
		};
	}
}