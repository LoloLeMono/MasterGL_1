package decorateur;

public class Compte extends AbstractCompte
{
	Client cl;

	public Compte(Client cl)
	{
		this.cl = cl;
	}
	
	public float prixLocation(Produit p)
	{
		return p.prixLocation();
	}
}
