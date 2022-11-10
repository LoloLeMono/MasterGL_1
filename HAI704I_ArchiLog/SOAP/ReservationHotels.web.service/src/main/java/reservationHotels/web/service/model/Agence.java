package reservationHotels.web.service.model;

import java.time.LocalDate;
// import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
// import java.util.Optional;
import java.util.Optional;

import javax.jws.WebService;

import reservationHotels.web.service.services.IAgenceService;

@WebService(endpointInterface="reservationHotels.web.service.services.IAgenceService")
public class Agence implements IAgenceService
{
	/* ATTRIBUTES */
	private String nom;
	private ArrayList<Hotel> li_HotelsPartenaire;
	
	/* CONSTRUCTOR */
	public Agence(String nom, ArrayList<Hotel> li_HotelsPartenaire)
	{
		this.nom = nom;
		this.li_HotelsPartenaire = li_HotelsPartenaire;
	}

	/* METHODS */
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
	
	@Override
	public ArrayList<Hotel> addHotelPartenaire(Hotel h)
	{
		this.getLi_HotelsPartenaire().add(h);
		
		return this.getLi_HotelsPartenaire();
	}

	@Override
	public ArrayList<Hotel> removeHotelPartenaire(Hotel h)
	{
		Optional<Hotel> hotel = this.getLi_HotelsPartenaire()
				.stream()
				.filter(e -> e == h)
				.findFirst();
		
		if(hotel == null)
		{
			System.err.print("Impossible de supprimer l'hotel");
		}
		
		return this.getLi_HotelsPartenaire();
	}

	@Override
	public ArrayList<Chambre> recherche(String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax,
			int nbEtoiles, int nbLits)
	{
		ArrayList<Chambre> li_chambreResult = new ArrayList<Chambre>();
		
		for (Hotel h : this.getLi_HotelsPartenaire())
		{
			if (h.getAdresse().getVille() == ville & h.getNbEtoiles() == nbEtoiles & h.getNbPlaces() >= nbLits)
			{
				for (Chambre c : h.getChambres())
				{
					if (c.getPrix() >= prixMin && c.getPrix() <= prixMax && c.estLibre(dateArr, dateDep))
					{
						li_chambreResult.add(c);
					}
				}
			}
		}
		
		return li_chambreResult;
	}
}