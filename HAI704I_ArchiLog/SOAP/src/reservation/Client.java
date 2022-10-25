package reservation;

import java.util.ArrayList;
import java.util.Date;

public class Client
{
	public String nom;
	public String prenom;
	
	public long numCard;
	public int cryptoCard;
	public ArrayList<Hotel> hotelDispo;
	
	public Client(String nom, String prenom, int numCard, int cryptoCard)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numCard = numCard;
		this.cryptoCard = cryptoCard;
	}
	
	public ArrayList<Hotel> demandeDispo(Agence ag, String ville, Date dateArr, Date dateDep, int prixMin, int prixMax, int nbEtoiles, int nbLits)
	{
		return ag.rechercheHotel(ville, dateArr, dateDep, prixMin, prixMax, nbEtoiles, nbLits);
	}
	
	public void reservation(Hotel h)
	{
		
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
	
	public long getNumCard()
	{
		return numCard;
	}
	
	public void setNumCard(int numCard)
	{
		this.numCard = numCard;
	}
	
	public int getCryptoCard()
	{
		return cryptoCard;
	}
	
	public void setCryptoCard(int cryptoCard)
	{
		this.cryptoCard = cryptoCard;
	}
	
	
}
