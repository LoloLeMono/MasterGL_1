package reservationHotels.web.service.model;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Optional;

public class Agence
{
	/* ATTRIBUTES */
	public String nom;
	public ArrayList<Hotel> li_HotelsPartenaire;
	
	/* CONSTRUCTOR */
	public Agence(String nom, ArrayList<Hotel> li_HotelsPartenaire)
	{
		super();
		this.nom = nom;
		this.li_HotelsPartenaire = li_HotelsPartenaire;
	}

	/* METHODS */
	public ArrayList<Chambre> recherche(String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax, int nbEtoiles, int nbLits)
	{
		ArrayList<Chambre> li_chambreResult = new ArrayList<Chambre>();
		
		for (Hotel h : li_HotelsPartenaire)
		{
			if (h.adresse.ville == ville & h.nbEtoiles == nbEtoiles & h.nbPlaces >= nbLits)
			{
				for (Chambre c : h.li_Chambres)
				{
					if (c.prix >= prixMin && c.prix <= prixMax && c.estLibre(dateArr, dateDep))
					{
						li_chambreResult.add(c);
					}
				}
			}
		}
		
		return li_chambreResult;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public ArrayList<Hotel> getLi_HotelsPartenaire()
	{
		return li_HotelsPartenaire;
	}

	public void setLi_HotelsPartenaire(ArrayList<Hotel> li_HotelsPartenaire)
	{
		this.li_HotelsPartenaire = li_HotelsPartenaire;
	}
	
	public ArrayList<Hotel> addHotelPartenaire(Hotel h)
	{
		this.li_HotelsPartenaire.add(h);
		
		return this.li_HotelsPartenaire;
	}
	
	public ArrayList<Hotel> removeHotelPartenaire(Hotel h)
	{
		Optional<Hotel> hotel = li_HotelsPartenaire
				.stream()
				.filter(e -> e == h)
				.findFirst();
		
		if(hotel == null)
		{
			System.err.print("Impossible de supprimer l'hotel");
		}
		
		return this.li_HotelsPartenaire;
	}
}