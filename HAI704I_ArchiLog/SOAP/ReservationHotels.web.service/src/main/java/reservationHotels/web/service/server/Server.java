package reservationHotels.web.service.server;

import java.util.ArrayList;

import javax.xml.ws.Endpoint;

import reservationHotels.web.service.model.Adresse;
import reservationHotels.web.service.model.Agence;
import reservationHotels.web.service.model.Chambre;
import reservationHotels.web.service.model.Hotel;

public class Server
{
	static ArrayList<Hotel> li_Hotels;
	
	public static void main(String[] args)
	{
		li_Hotels = new ArrayList<Hotel>();
		
		li_Hotels.add(new Hotel("Mercure", new Adresse("France", "Montepllier", 5, "Rue des arbes"), 3));
		li_Hotels.get(0).getChambres().add(new Chambre(2, 20, 100, li_Hotels.get(0)));
		
		li_Hotels.add(new Hotel("Ibis", new Adresse("France", "Lamalou", 10, "Rue des platanes"), 2));
		li_Hotels.get(1).getChambres().add(new Chambre(1, 10, 20, li_Hotels.get(1)));
		
		li_Hotels.add(new Hotel("DeFou", new Adresse("USA", "Los Angeles", 5, "Street of three"), 5));
		li_Hotels.get(2).getChambres().add(new Chambre(5, 50, 300, li_Hotels.get(2)));
		
		agencePublisher("Agence-1", li_Hotels);
		
		System.err.println("Server ready");
	}
	
	public static void agencePublisher(String name, ArrayList<Hotel> li_HotelsPartenaire)
	{
		Agence agence = new Agence(name, li_HotelsPartenaire);
		li_Hotels.clear();
		Endpoint.publish("http://localhost:8080/Agence/" + name, agence);
		System.out.println("New agency publish : "+ name);
	}

}
