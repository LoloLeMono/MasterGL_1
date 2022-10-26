package double_dispatch;

import java.util.ArrayList;

public class Compte
{
	Client cl;
	ArrayList<Produit> l_locations;

	public Compte(Client cl)
	{
		super();
		this.cl = cl;
		this.l_locations = new ArrayList<Produit>();
	}
	
	public Double prixLocation(Produit p)
	{
		// System.out.println("Produit : " + p.getNom());
		// System.out.println("Prix : " + p.prixLocation());
		
		return p.prixLocation();
	}
}
