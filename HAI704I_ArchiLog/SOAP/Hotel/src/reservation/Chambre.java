package reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Chambre
{
	public Integer nbLits;
	public Integer superficie;
	public Integer prix;
	
	public Chambre(Integer nbLits, Integer superficie, Integer prix)
	{
		super();
		this.nbLits = nbLits;
		this.superficie = superficie;
		this.prix = prix;
	}


	public Integer getNbLits()
	{
		return nbLits;
	}
	
	public void setNbLits(Integer nbLits)
	{
		this.nbLits = nbLits;
	}
	
	public Integer getSuperficie()
	{
		return superficie;
	}
	
	public void setSuperficie(Integer superficie)
	{
		this.superficie = superficie;
	}
	
	public Integer getPrix()
	{
		return prix;
	}
	
	public void setPrix(Integer prix)
	{
		this.prix = prix;
	}
}
