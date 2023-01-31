package reservation;

import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.Date;

public class Client
{
	public String nom;
	public String prenom;
	
	public long numCard;
	public int cryptoCard;
	public ArrayList<Chambre> li_chambreReserve;
	
	public Client(String nom, String prenom, long numCard, int cryptoCard)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numCard = numCard;
		this.cryptoCard = cryptoCard;
		
		this.li_chambreReserve = new ArrayList<Chambre>();
	}
	
	public ArrayList<Chambre> demandeDispo(Agence ag, String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax, int nbEtoiles, int nbLits)
	{
		return ag.recherche(ville, dateArr, dateDep, prixMin, prixMax, nbEtoiles, nbLits);
	}
	
	public void reservation(Chambre c, LocalDate dateArr, LocalDate dateDep)
	{
		while (!dateArr.equals(dateDep))
		{
			c.li_dateReserve.add(dateArr);
			dateArr = dateArr.plusDays(1);
		}
		
		c.li_dateReserve.add(dateArr);
		
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
