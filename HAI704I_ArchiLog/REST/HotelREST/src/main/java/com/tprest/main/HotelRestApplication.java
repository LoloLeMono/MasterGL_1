package com.tprest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages =
{
		"com.tprest.models"
})

@EnableJpaRepositories(basePackages = 
{
		"com.tprest.repositories"
})

@SpringBootApplication(scanBasePackages = 
{
	"com.tprest.data",
	"com.tprest.exceptions",
	"com.tprest.controllers"
})
public class HotelRestApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(HotelRestApplication.class, args);
	}

}
