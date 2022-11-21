package reservation;

import java.util.ArrayList;

public class Hotel
{
	public String nom;
	public Adresse adresse;
	public int nbEtoiles;
	
	public ArrayList<Chambre> li_Chambres;
	public int nbPlaces;
	
	public Hotel(String nom, Adresse adr, int nbEtoiles)
	{
		super();
		this.nom = nom;
		this.adresse = adr;
		this.nbEtoiles = nbEtoiles;
		
		this.li_Chambres = new ArrayList<Chambre>();
		this.nbPlaces = 0;
	}
	
	public void addChambre(Integer nbLits, Integer superficie, Integer prix)
	{
		Chambre c = new Chambre(nbLits, superficie, prix, this);
		this.li_Chambres.add(c);
		nbPlaces+=nbLits;
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
}
