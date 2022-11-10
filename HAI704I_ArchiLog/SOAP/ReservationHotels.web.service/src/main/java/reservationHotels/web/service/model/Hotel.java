package reservationHotels.web.service.model;

import java.util.ArrayList;

//import reservationHotels.web.service.model.Adresse;
//import reservationHotels.web.service.model.Chambre;

public class Hotel
{
	private String nom;
	private Adresse adresse;
	private int nbEtoiles;
	
	private ArrayList<Chambre> li_Chambres;
	private int nbPlaces;
	
	public Hotel(String nom, Adresse adr, int nbEtoiles)
	{
		super();
		this.nom = nom;
		this.adresse = adr;
		this.nbEtoiles = nbEtoiles;
		
		this.li_Chambres = new ArrayList<Chambre>();
		this.nbPlaces = 0;
	}

	public void setNbPlaces(int nbPlaces)
	{
		this.nbPlaces = nbPlaces;
	}
	
	public int getNbPlaces()
	{
		return nbPlaces;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public Adresse getAdresse()
	{
		return adresse;
	}
	
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}
	
	public int getNbEtoiles()
	{
		return nbEtoiles;
	}
	
	public void setNbEtoiles(int nbEtoiles)
	{
		this.nbEtoiles = nbEtoiles;
	}
	
	public ArrayList<Chambre> getChambres()
	{
		return li_Chambres;
	}
	
	public void setChambres(ArrayList<Chambre> li_c)
	{
		this.li_Chambres = li_c;
	}
}