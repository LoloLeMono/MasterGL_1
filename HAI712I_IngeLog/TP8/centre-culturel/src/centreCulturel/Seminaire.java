package centreCulturel;

import java.util.ArrayList;
import java.util.HashMap;

public class Seminaire
{
	//protected AbstractEtatSeminaux[] etats = new AbstractEtatSeminaux[3];
	static protected enum nomEtats {planification, placeDisponible, complet, preparationSeminaire, enCours};
	protected HashMap<nomEtats,AbstractEtatSeminaux> etats;
	private AbstractEtatSeminaux etatCourant;
	
	String titre, resume;
	int capacite;
	ArrayList<String> inscrit;
	
	public Seminaire()
	{
		// etats[0] = new EtatPlanification(this);
		// etats[1] = new EtatPlaceDispo(this);
		// etats[2] = new EtatPreparationSeminaire(this);
		etats.put(nomEtats.planification, new EtatPlanification(this));
		etats.put(nomEtats.placeDisponible, new EtatPlaceDispo(this));
		etats.put(nomEtats.complet, new EtatComplet(this));
		etats.put(nomEtats.preparationSeminaire, new EtatPreparationSeminaire(this));
		
		// etatCourant = etats[0];
		etatCourant = etats.get(nomEtats.planification);
	}
	
	String getTitre() {return this.titre;}
	String getResume() {return this.resume;}
	Integer getCapacite() {return this.capacite;}
	
	void setTitre(String titre) { this.titre = titre;}
	void setResume(String resume) { this.resume = resume;}
	void setCapacite(int capacite) { this.capacite = capacite;}
	void addNom(String nom) {this.inscrit.add(nom);}

	public void donnerTitre(String titre) {
		nomEtats etatSuivant = etatCourant.donnerTitre(titre);
	}
	
	public void ouvertureReservation()
	{
		nomEtats etatSuivant = etatCourant.ouvertureReservation();
		etatCourant = etats.get(nomEtats.planification);
	}

	public void inscription(String nom)
	{
		nomEtats etatSuivant = etatCourant.inscription(nom);
		etatCourant = etats.get(nomEtats.placeDisponible);
	}
}
