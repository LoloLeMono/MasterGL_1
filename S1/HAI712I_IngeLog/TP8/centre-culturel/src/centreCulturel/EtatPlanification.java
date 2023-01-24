package centreCulturel;

import centreCulturel.Seminaire.nomEtats;

public class EtatPlanification extends AbstractEtatSeminaux
{
	EtatPlanification(Seminaire s)
	{
		super(s);
	}

	@Override
	public void clotureReservation()
	{
		// TODO Auto-generated method stub
	}

	@Override
	nomEtats ouvertureReservation()
	{
		sem.setTitre("Titre");
		sem.setResume("Resume seminaire");
		sem.setCapacite(10);
		
		return nomEtats.placeDisponible;
	}

	@Override
	protected nomEtats inscription(String nom)
	{
		sem.addNom(nom);
		sem.setCapacite(sem.capacite - 1);
		
		nomEtats suivant;
		
		if (sem.getCapacite() == 0)
		{
			suivant = nomEtats.complet;
		}
		else
		{
			suivant = nomEtats.placeDisponible;
		}
		
		return suivant;
	}

	public void donnerTitre(String titre) throws Exception {
		sem.setTitre(titre);
	}
}
