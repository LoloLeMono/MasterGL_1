package decorateur;

public class ForfaitReduction extends Forfait
{

	private float tauxReduc;

	public ForfaitReduction(AbstractCompte c)
	{
		super(c);
		this.tauxReduc = (float) 10;
	}

	@Override
	public float prixLocation(Produit p)
	{
		float res = super.prixLocation(p);
		return res - (res*this.tauxReduc)/100;
	}

}
