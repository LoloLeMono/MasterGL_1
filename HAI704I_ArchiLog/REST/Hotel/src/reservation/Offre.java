package reservation;

import java.time.LocalDate;
import java.util.ArrayList;

public class Offre
{
	int id;
	ArrayList<LocalDate> datesDispo;
	int nbLits;
	int prix;
	
	public Offre(int idOffre, ArrayList<LocalDate> datesDispo, int nbLits, int prix)
	{
		id = idOffre;
		this.datesDispo = datesDispo;
		this.nbLits = nbLits;
		this.prix = prix;
	}
}
