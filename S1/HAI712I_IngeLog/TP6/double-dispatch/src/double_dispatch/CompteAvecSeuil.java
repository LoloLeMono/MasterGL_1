package double_dispatch;

public class CompteAvecSeuil extends Compte
{
	Integer nbLoc;
	Integer seuil;

	public CompteAvecSeuil(Client cl, Integer seuil)
	{
		super(cl);
		this.seuil = seuil;
		this.nbLoc = 0;
	}
	
	@Override
	public Double prixLocation(Produit p)
	{
		// System.out.println("Produit : " + p.getNom());
		// System.out.println("Prix de base : " + p.prixLocation());
		
		Double prix = p.prixLocation();
		
		if (nbLoc%seuil == 0 && nbLoc != 0)
		{
			// System.out.println("Seuil atteint ! Location gratuite");
			prix = 0.0;
		}
		
		nbLoc ++;
		return prix;
	}

}
