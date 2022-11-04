package reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
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
<<<<<<< HEAD
		
		System.out.println("Creation d'une agence Agence_1.");
		Agence agence = new Agence("Agence_1", li_Hotels);
		
		System.out.println("Creation d'un client Max Holloway.");
=======
		
		System.out.println("Creation d'une agence : Agence_1");
		Agence agence = new Agence("Agence_1", li_Hotels);
		
		System.out.println("Creation d'un client : Max Holloway");
>>>>>>> 9ec7ccaac2542e72b6decc5ac48c9de23f9d75fa
		li_Clients.add(new Client("Holloway", "Max", 1234_5678_9012_3456L, 587));
		
		LocalDate dateArr = LocalDate.of(2022, 11, 1);
		LocalDate dateDep = LocalDate.of(2022, 11, 5);
		
		System.out.println("Critère de recherche du client : ");
		System.out.println("	Dispo entre le 1/11/2022 et le 5/11/2022");
		System.out.println("	Paris");
		System.out.println("	Entre 100 et 800€");
		System.out.println("	4 étoiles");
		System.out.println("	2 lits");
		
		ArrayList<Chambre> li_chambreDispo = new ArrayList<Chambre>();
		li_chambreDispo = li_Clients.get(0).demandeDispo(agence, "Paris", dateArr, dateDep, 100, 800, 4, 2);
		System.out.println("Resultat recherche : " + li_chambreDispo.get(0).toString());
		
		li_Clients.get(0).reservation(li_chambreDispo.get(0), dateArr, dateDep);
		
		System.out.println("La chambre est réservé pour ces dates : " + li_chambreDispo.get(0).li_dateReserve);
		System.out.println("Le client à reservé la chambre : " + li_Clients.get(0).li_chambreReserve.get(0) + " de l'hotel : " + li_Clients.get(0).li_chambreReserve.get(0).getH().getNom());
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
