package common;

public class Lambda implements ILambda
{

	// private static final long serialVersionUID = 5151987123687421365L;
	
	String nom;
	String nomMaitre;
	String race;
	String dossierSuivi;
	String especeClass;
	
	
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setNomMaitre(String nom) {
		this.nomMaitre = nom;
	}

	@Override
	public void setRace(String nom) {
		this.race = nom;
	}

	@Override
	public void setDossierSuivi(String nom) {
		this.dossierSuivi = nom;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getNomMaitre() {
		return nomMaitre;
	}

	@Override
	public String getRace() {
		return race;
	}

	@Override
	public String getDossierSuivi() {
		return dossierSuivi;
	}

}