package double_dispatch;

public class ProduitSolde extends Produit
{
	
	public ProduitSolde(String nom, Double prix)
	{
		super(nom+" (solde)", prix);
	}
	
	@Override
	public void setNom(String nom)
	{
		this.setNom(nom + " (solde)");
	}
	
	public Double prixLocation() //DOUBLE-DISPATCH
	{
		return super.prixLocation()/2;
	}

}
