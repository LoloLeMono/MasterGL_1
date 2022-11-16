package hotel.web.service.services;

import java.util.ArrayList;

import javax.xml.ws.Endpoint;

import hotel.web.service.model.Adresse;
import hotel.web.service.model.Chambre;
import hotel.web.service.model.Hotel;
import hotel.web.service.model.Agence;

public class HotelServicePublisher
{

	public static void main(String[] args)
	{
		hotelPublisher("Hotel1", new Adresse("France", "Montpellier", 30, "Rue triolet"), 3, new ArrayList<Agence>());

		ArrayList<Agence> partner = new ArrayList<Agence>();
		partner.add(new Agence("Agence", "123", 30));
		partner.add(new Agence("Agence5", "123", 50));
		hotelPublisher("Hotel2", new Adresse("Espagne", "Barcelone", 2, "Rue Gerard Piquet"), 5, partner);
		
		hotelPublisher("Hotel3", new Adresse("France", "Paris", 12, "Rue de la paix"), 1, new ArrayList<Agence>());
		
		partner = new ArrayList<Agence>();
		partner.add(new Agence("Agence", "123", 15));
		partner.add(new Agence("Agence44", "123", 90));
		hotelPublisher("Hotel4", new Adresse("France", "Marseille", 1, "Quartier nord"), 4, partner);
		
		System.out.println("Server is ready");
	}
	
	public static void hotelPublisher(String name, Adresse adr, int etoiles, ArrayList<Agence> partenaires)
	{
		// Creation de l'hotel
		Hotel h = new Hotel(name, adr, etoiles);
		
		// Creation de n chambres
		int n = (int)(Math.random() * ((10 - 1) + 1)) + 1; //(max - min + 1) + min
		
		for(int i = 0; i < n; i++)
		{
			int random = (int)(Math.random() * ((4 - 1) + 1)) + 1; //(max - min + 1) + min
			int random1 = (int)(Math.random() * ((150 - 25) + 1)) + 25;
			
			Chambre c = new Chambre("Chambre " + i + ".", random, random1);
			h.getChambres().add(c);
		}
		
		// Ajout des partenaires
		h.getPartenaires().addAll(partenaires);
		System.out.println(h);
		
		// Publication
		Endpoint.publish("http://localhost:8080/" + name, h);
	}
}