package reservation;

import java.util.ArrayList;

public class Hotel
{
	public String nom;
	public String adresse;
	public int nbEtoiles;
	public int nbPlaces;
	
	public ArrayList<Chambre> li_Chambres;
	
	public Hotel(String nom, String adresse, int prix, int nbEtoiles, int nbLits)
	{
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.nbEtoiles = nbEtoiles;
		this.li_Chambres = new ArrayList<Chambre>();
	}
	
	public void addChambre(Chambre x)
	{
		this.li_Chambres.add(x);
	}

	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public void setAdresse(String adresse)
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
