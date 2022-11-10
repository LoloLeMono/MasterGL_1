package decorateur;

public class ForfaitSeuil extends Forfait
{
	private int nbLoc;
	private final int seuil;

	public ForfaitSeuil(AbstractCompte c)
	{
		super(c);
		this.nbLoc = 0;
		this.seuil = 2;
	}
	
	@Override
	public float prixLocation(Produit p)
	{
		float res = super.prixLocation(p);
		
		if (this.nbLoc%seuil == 0 && nbLoc != 0)
		{
			// System.out.println("Seuil atteint ! Location gratuite");
			res = (float) 0;
			this.nbLoc = -1;
		}
		
		nbLoc ++;
		return res;
	}

}
