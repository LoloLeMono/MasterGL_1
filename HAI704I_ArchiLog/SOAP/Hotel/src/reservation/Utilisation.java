package reservation;

import java.util.ArrayList;
import java.util.Random; //(int) (Math.random() * (max - min)) + min;
import java.util.Scanner;

public class Utilisation
{
	static ArrayList<Hotel> li_Hotels;
	Scanner sc = new Scanner(System.in);
	// String age = sc.nextLines();
	
	public static void main(String[] args)
	{
		li_Hotels = new ArrayList<Hotel>();
		createHotels(3);
	}
	
	public static void createHotels(Integer nbHotels)
	{
		String nom;
		Adresse adr;
		Integer nbEtoiles, nbPlaces;
		
		for (int i=0; i<nbHotels; i++)
		{
			nom = "Hotel_"+i;
			adr = null;
			nbEtoiles = (int) (Math.random() * 5);
			nbPlaces = (int) (Math.random() * (20 - 5)) + 5;
			
			Hotel h = new Hotel(nom, adr, nbEtoiles, nbPlaces);
			
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
			
			li_Hotels.add(h);
		}
	}
}
