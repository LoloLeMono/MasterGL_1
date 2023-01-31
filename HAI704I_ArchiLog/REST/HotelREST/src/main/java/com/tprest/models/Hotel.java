package com.tprest.models;

import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Hotel
{
	/* ATTRIBUTES */
	@Id
	@GeneratedValue
	private long id;
	private String nom;
	private String adresse;
	private int nbEtoiles;
	
	//private ArrayList<Chambre> li_Chambres;
	//private int nbPlaces;
	
	/* CONSTRUCTORS */
	public Hotel()
	{
		
	}
	
	public Hotel(String nom, String adr, int nbEtoiles)
	{
		this.nom = nom;
		this.adresse = adr;
		this.nbEtoiles = nbEtoiles;
		
		//this.li_Chambres = new ArrayList<Chambre>();
		//this.nbPlaces = 0;
	}
	
	/* GET - SET */
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
	
	/*
	public void setNbPlaces(int nbPlaces)
	{
		this.nbPlaces = nbPlaces;
	}
	
	public int getNbPlaces()
	{
		return nbPlaces;
	}
	*/
	
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
	
	/*
	public void setChambre(ArrayList<Chambre> li_Chambres)
	{
		this.li_Chambres = li_Chambres;
	}
	
	public ArrayList<Chambre> getChambres()
	{
		return li_Chambres;
	}
	*/
	
	
	@Override
	public int hashCode()
	{
		return Objects.hash(adresse, /*li_Chambres,*/ nbEtoiles,/* nbPlaces,*/ nom);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Hotel other = (Hotel) obj;
		
		return Objects.equals(adresse, other.adresse) /*&& Objects.equals(li_Chambres, other.li_Chambres)*/
				&& nbEtoiles == other.nbEtoiles && /*nbPlaces == other.nbPlaces && */Objects.equals(nom, other.nom);
	}

	/*
	@Override
	public String toString()
	{
		return "Hotel [nom=" + nom + ", adresse=" + adresse + ", nbEtoiles=" + nbEtoiles + ", li_Chambres="
				+ li_Chambres + ", nbPlaces=" + nbPlaces + "]";
	}
	*/
	
	@Override
	public String toString()
	{
		return "Hotel [nom=" + nom + ", adresse=" + adresse + ", nbEtoiles=" + nbEtoiles /*+ ", nbPlaces=" + nbPlaces */+ "]";
	}
	
	/* METHODS */
	/*
	public void addChambre(Integer nbLits, Integer prix, Hotel h)
	{
		Chambre c = new Chambre(nbLits, prix, h);
		this.li_Chambres.add(c);
		nbPlaces+=nbLits;
	}
	*/
	
}
