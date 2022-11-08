package reservationHotels.web.service.services;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.jws.WebService;

import reservationHotels.web.service.model.Chambre;
import reservationHotels.web.service.model.Hotel;

@WebService(endpointInterface="reservationHotels.web.service.services.IAgenceService")
public class AgenceService implements IAgenceService {

	public AgenceService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Hotel> getLi_HotelsPartenaire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLi_HotelsPartenaire(ArrayList<Hotel> li_HotelsPartenaire) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Hotel> addHotelPartenaire(Hotel h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Hotel> removeHotelPartenaire(Hotel h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Chambre> recherche(String ville, LocalDate dateArr, LocalDate dateDep, int prixMin, int prixMax,
			int nbEtoiles, int nbLits) {
		// TODO Auto-generated method stub
		return null;
	}

}
