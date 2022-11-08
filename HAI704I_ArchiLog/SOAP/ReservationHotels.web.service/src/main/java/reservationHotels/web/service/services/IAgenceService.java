package reservationHotels.web.service.services;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import reservationHotels.web.service.model.Chambre;
import reservationHotels.web.service.model.Hotel;

@WebService
public interface IAgenceService
{
	/* METHODS */
	@WebMethod
	String getNom();
	
	@WebMethod
	void setNom(String nom);
	
	@WebMethod
	ArrayList<Hotel> getLi_HotelsPartenaire();
	
	@WebMethod
	void setLi_HotelsPartenaire(ArrayList<Hotel> li_HotelsPartenaire);
	
	@WebMethod
	ArrayList<Hotel> addHotelPartenaire(Hotel h);
	
	@WebMethod
	ArrayList<Hotel> removeHotelPartenaire(Hotel h);
	
	@WebMethod
	ArrayList<Chambre> recherche(String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax, int nbEtoiles, int nbLits);

	
}
