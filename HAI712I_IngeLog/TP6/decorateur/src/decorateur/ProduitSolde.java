package decorateur;

public class ProduitSolde extends Produit
{
	
	public ProduitSolde(String nom, float prix)
	{
		super(nom+" (solde)", prix);
	}
	
	@Override
	public void setNom(String nom)
	{
		this.setNom(nom + " (solde)");
	}
	
	public float prixLocation()
	{
		return super.prixLocation()/2;
	}

}
