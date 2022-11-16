package hotel.web.service.client.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import hotel.web.service.client.HotelService;
import hotel.web.service.client.IHotelService;

public class Main
{

	//static ArrayList<Hotel> hotels;
	//static ArrayList<Agence> agences;
		
	public static void main(String[] args) throws MalformedURLException
	{
		URL urlHotel1 = new URL("http://localhost:8080/Hotel1?wsdl");
		URL urlHotel2 = new URL("http://localhost:8080/Hotel2?wsdl");
		URL urlHotel3 = new URL("http://localhost:8080/Hotel3?wsdl");
		URL urlHotel4 = new URL("http://localhost:8080/Hotel4?wsdl");
		
		ArrayList<IHotelService> proxys = new ArrayList<IHotelService>();
		
		IHotelService proxyH1 = new HotelService(urlHotel1).getHotelPort();
		IHotelService proxyH2 = new HotelService(urlHotel2).getHotelPort();
		IHotelService proxyH3 = new HotelService(urlHotel3).getHotelPort();
		IHotelService proxyH4 = new HotelService(urlHotel4).getHotelPort();
		
		proxys.add(proxyH1);
		proxys.add(proxyH2);
		proxys.add(proxyH3);
		proxys.add(proxyH4);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Recherche hotel : ");
		System.out.println("Entrer une ville ?");
		String ville = "";
		do {
			ville = sc.nextLine();
		}while (ville.equals(""));
		
		System.out.println("Entrer une date d'arrivée (aaaa/mm/jj)");
		String dateArr = "";
		do {
			dateArr = sc.nextLine();
		}while (dateArr.equals(""));
		
		
		System.out.println("Entrer une date de depart (aaaa/mm/jj)");
		String dateDep = "";
		do {
			dateDep = sc.nextLine();
		}while (dateDep.equals(""));	
		
		
		System.out.println("Entrer prix min");
		int prixMin = sc.nextInt();
	
		System.out.println("Entrer prix max");
		int prixMax = sc.nextInt();
		
		System.out.println("Entrer le nb d'etoiles de l'hotel");
		int etoiles = sc.nextInt();
		
		System.out.println("Entrer le nb de lits");
		int lits = sc.nextInt();
		
		ArrayList<String> offres = new ArrayList<String>();
		
		for (IHotelService p : proxys)
		{
			offres = (ArrayList<String>) p.rechercher("", "", dateArr, dateDep, prixMin, prixMax, lits);
		}
		
		System.out.println("Voici les offres qui correspondent à votre recherche :");
		System.out.println(offres);
		
	}
}