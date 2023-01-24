package double_dispatch;

public class CompteAvecReduction extends Compte
{
	Float tauxReduc;
	
	public CompteAvecReduction(Client cl, float taux)
	{
		super(cl);
		this.tauxReduc = taux;
	}
	
	@Override
	public Double prixLocation(Produit p)
	{
		// System.out.println("Produit : " + p.getNom());
		// System.out.println("Prix de base : " + p.prixLocation());
		// System.out.println("Réduction 10% avec le compte Reduction !");
		// System.out.println("Prix TOTAL : " + (p.prixLocation() - (p.prixLocation()*tauxReduc)/100));
		
		return p.prixLocation() - (p.prixLocation()*this.tauxReduc)/100;
	}

}
