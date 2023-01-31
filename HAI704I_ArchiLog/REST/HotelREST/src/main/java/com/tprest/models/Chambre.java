package com.tprest.models;

import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Chambre
{
	/* ATTRIBUTES */
	@Id
	@GeneratedValue
	private long id;
	private Integer nbLits;
	private Integer prix;
	private Hotel h;
	private ArrayList<LocalDate> li_dateReserve;
	
	/* CONSTRUCTORS */
	public Chambre()
	{
		
	}
	
	public Chambre(Integer nbLits, Integer prix, Hotel h)
	{
		this.nbLits = nbLits;
		this.prix = prix;
		this.h = h;
		this.li_dateReserve = new ArrayList<LocalDate>();
	}
	
	/* GET - SET */
	public Hotel getH()
	{
		return h;
	}

	public void setH(Hotel h)
	{
		this.h = h;
	}


	public Integer getNbLits()
	{
		return nbLits;
	}
	
	public void setNbLits(Integer nbLits)
	{
		this.nbLits = nbLits;
	}
	
	public ArrayList<LocalDate> getLi_dateReserve()
	{
		return li_dateReserve;
	}
	
	public void setLi_dateReserve(ArrayList<LocalDate> li)
	{
		this.li_dateReserve = li;
	}
	
	public Integer getPrix()
	{
		return prix;
	}
	
	public void setPrix(Integer prix)
	{
		this.prix = prix;
	}
	
	
	@Override
	public int hashCode()
	{
		return Objects.hash(h, id, li_dateReserve, nbLits, prix);
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
		
		Chambre other = (Chambre) obj;
		
		return Objects.equals(h, other.h) && id == other.id && Objects.equals(li_dateReserve, other.li_dateReserve)
				&& Objects.equals(nbLits, other.nbLits) && Objects.equals(prix, other.prix);
	}

	@Override
	public String toString()
	{
		return "Chambre de avec " + nbLits + " lits pour " + prix + "€ par nuit";
	}
	
	/* METHODS */
	public Boolean estLibre(LocalDate dateArr, LocalDate dateDep)
	{
		ArrayList<LocalDate> li_dateClient = new ArrayList<LocalDate>();
		LocalDate dateBuff;
		Boolean trigg = true;
		
		dateBuff = dateArr;
		
		while(!dateBuff.equals(dateDep.plusDays(1)))
		{
			li_dateClient.add(dateBuff);
			dateBuff = dateBuff.plusDays(1);
		}
		
		
		for (int i = 0; i<this.li_dateReserve.size(); i++)
		{
			for (int j=0; j<li_dateClient.size(); j++)
			{
				if (li_dateReserve.get(i).equals(li_dateClient.get(j)))
				{
					trigg = false;
				}
			}
		}
		
		return trigg;
		
	}
}
