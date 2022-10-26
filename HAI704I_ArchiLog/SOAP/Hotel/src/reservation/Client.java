package reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Client
{
	public String nom;
	public String prenom;
	
	public long numCard;
	public int cryptoCard;
	public ArrayList<Chambre> li_chambreReserve;
	
	public Client(String nom, String prenom, int numCard, int cryptoCard)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numCard = numCard;
		this.cryptoCard = cryptoCard;
	}
	
	public ArrayList<Chambre> demandeDispo(Agence ag, String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax, int nbEtoiles, int nbLits)
	{
		return ag.recherche(ville, dateArr, dateDep, prixMin, prixMax, nbEtoiles, nbLits);
	}
	
	public void reservation(Chambre c, LocalDate dateDep)
	{
		c.dateLibre = dateDep;
		li_chambreReserve.add(c);
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
