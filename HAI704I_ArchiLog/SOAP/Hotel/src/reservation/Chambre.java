package reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Chambre
{
	public Integer nbLits;
	public Integer superficie;
	public Integer prix;
	ArrayList<LocalDate> li_dateReserve;
	
	public Chambre(Integer nbLits, Integer superficie, Integer prix)
	{
		super();
		this.nbLits = nbLits;
		this.superficie = superficie;
		this.prix = prix;
		this.li_dateReserve = new ArrayList<LocalDate>();
	}
	
	public Boolean estLibre(LocalDate dateArr, LocalDate dateDep)
	{
		ArrayList<LocalDate> li_dateClient = new ArrayList<LocalDate>();
		LocalDate dateBuff;
		Boolean trigg = true;
		
		dateBuff = dateArr;
		
		while(dateBuff != dateDep)
		{
			li_dateClient.add(dateBuff);
			dateBuff.plusDays(1);
		}
		
		
		for (int i = 0; i<this.li_dateReserve.size(); i++)
		{
			for (int j=0; j<li_dateClient.size(); j++)
			{
				if (li_dateReserve.get(i) == li_dateClient.get(j))
				{
					trigg = false;
				}
			}
		}
		
		return trigg;
		
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
