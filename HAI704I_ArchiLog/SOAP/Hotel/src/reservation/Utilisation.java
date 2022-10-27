package reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random; //(int) (Math.random() * (max - min)) + min;
import java.util.Scanner;

public class Utilisation
{
	Scanner sc = new Scanner(System.in);
	// String age = sc.nextLines();
	
	public static void main(String[] args)
	{
		
		ArrayList<Hotel> li_Hotels = new ArrayList<Hotel>();
		ArrayList<Client> li_Clients = new ArrayList<Client>();
		Adresse adr = new Adresse("France", "Paris", 3, "Rue caca");
		
		li_Hotels.add(new Hotel("Hotel_Mercure", adr, 4));
		li_Hotels.get(0).addChambre(2, 100, 500);
		
		li_Hotels.add(new Hotel("Hotel_Ibis", adr, 2));
		li_Hotels.get(1).addChambre(2, 30, 50);
		li_Hotels.get(1).addChambre(2, 40, 70);
		li_Hotels.get(1).addChambre(1, 20, 30);
		
		Agence agence = new Agence("Agence_Hotels", li_Hotels);
		
		li_Clients.add(new Client("Rouquairol", "Lucas", 1234_5678_9012_3456L, 587));
		
		LocalDate dateArr = LocalDate.of(2022, 11, 1);
		LocalDate dateDep = LocalDate.of(2022, 11, 5);
		
		System.out.println("Chambres dispo : ");
		ArrayList<Chambre> li_chambreDispo = new ArrayList<Chambre>();
		li_chambreDispo = li_Clients.get(0).demandeDispo(agence, "Paris", dateArr, dateDep, 100, 800, 4, 2);
		System.out.println(li_chambreDispo.get(0).toString());
		
		System.out.println("FIN");
	}
	
	
	
	
	public static ArrayList<Hotel> createHotels(Integer nbHotels)
	{
		String nom;
		Adresse adr;
		Integer nbEtoiles, nbPlaces;
		
		ArrayList<Hotel> li = new ArrayList<Hotel>();
		
		for (int i=0; i<nbHotels; i++)
		{
			nom = "Hotel_"+i;
			adr = new Adresse("France", "Paris", 3, "Rue caca");
			nbEtoiles = (int) (Math.random() * 5);
			nbPlaces = (int) (Math.random() * (20 - 5)) + 5;
			
			Hotel h = new Hotel(nom, adr, nbEtoiles);
			
			while (nbPlaces>0)
			{
				if (nbPlaces == 1) // Il reste une place
				{
					h.addChambre(1, 100, (int) (Math.random() * (200 - 20) + 20));
					nbPlaces--;
				}
				else
				{
					h.addChambre(2, 100, (int) (Math.random() * (200 - 20) + 20));
					nbPlaces -= 2;
				}
			}
			
			li.add(h);
		}
		
		return li;
	}
	
	public static ArrayList<Client> createClients(Integer nbclient)
	{
		String nom, prenom;
		long numCard = 1234_5678_9012_3456L;
		Integer cryptoCard;
		
		ArrayList<Client> li = new ArrayList<Client>();
		
		for (int i=0; i<nbclient; i++)
		{
			nom = "Client_nom"+i;
			prenom = "Client_prenom"+i;
			cryptoCard = (int) (Math.random() * (999 - 1)) + 1;
			
			li.add(new Client(nom, prenom, numCard, cryptoCard));
		}
		
		return li;
	}
}
