package hotel.web.service.model;

import java.util.ArrayList;

public class Chambre
{
	
	private String nom;
	private Integer nbLits;
	private Integer prix;
		
	private ArrayList<Reservation> reservations;

	
	public Chambre(String nom, Integer nbLits, Integer prix)
	{
		this.nom = nom;
		this.nbLits = nbLits;
		this.prix = prix;
		reservations = new ArrayList<Reservation>();
	}
	
	@Override
	public String toString()
	{
		return "[nom=" + nom + ", nbLits=" + nbLits + ", prix=" + prix + ", reservation=" + reservations + "]";
	}

	public String getNom()
	{
		return nom;
	}
	
	public Integer getNbLits()
	{
		return nbLits;
	}
	
	public Integer getPrix(Integer reduc)
	{
		return prix - (prix * reduc/100);
	}
	
	public ArrayList<Reservation> getReservation()
	{
		return reservations;
	}	
}