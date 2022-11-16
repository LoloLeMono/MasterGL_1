package hotel.web.service.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IHotelService
{
	
	@WebMethod
	ArrayList<String> rechercher(String login, String mdp, String dateArr, String dateDep, int prixMin, int prixMax, int lits);

	@WebMethod
	String reserver(String nomchambre, String  nom, String prenom, double carte, String debut, String fin);
	
	@WebMethod
	Integer getReduction(String login, String mdp);

	
	@WebMethod
	String getAddresse();
	@WebMethod
	Integer getEtoiles();
	@WebMethod
	String getNom();
}