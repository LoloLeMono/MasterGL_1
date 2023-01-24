package common;

import java.io.Serializable;

public interface ILambda extends Serializable
{
	
	public void setNom(String nom);
	public void setNomMaitre(String nom);
	public void setRace(String nom);
	public void setDossierSuivi(String nom);
	
	public String getNom();
	public String getNomMaitre();
	public String getRace();
	public String getDossierSuivi();

}