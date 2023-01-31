package centreCulturel;

import centreCulturel.Seminaire.nomEtats;

public abstract class AbstractEtatSeminaux
{
	Seminaire sem;
	abstract nomEtats ouvertureReservation();
	
	AbstractEtatSeminaux(Seminaire s)
	{
		sem = s;
	}
	
	public abstract void clotureReservation();

	protected abstract nomEtats inscription(String nom);
	
	public void donnerTitre(String titre) throws Exception {
		throw new Exception("pas le droit");
	}
	
	public void donnerResume(String titre) throws Exception {
		throw new Exception("pas le droit");
	}
	
	public void donnerCapacite(String titre) throws Exception {
		throw new Exception("pas le droit");
	}
}
