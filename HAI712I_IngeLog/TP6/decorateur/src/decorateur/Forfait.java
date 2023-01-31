package decorateur;

public abstract class Forfait extends AbstractCompte
{
	private AbstractCompte decore;
	
	public Forfait(AbstractCompte c)
	{
		this.decore = c;
	}
	
	public float prixLocation(Produit p)
	{
		return decore.prixLocation(p);
	}
}
