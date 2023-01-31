package decorateur;

public class Utilisation
{

	public static void main(String[] args)
	{
		Produit lgv = new Produit("La grande vadrouille", (float) 10);
		Client cl = new Client("Dupont");
		
		//un compte normal pour le client Dupont
		AbstractCompte cmt = new Compte(cl);
		System.out.println("basique lgv : " + cmt.prixLocation(lgv));
		
		//Dupont achete un forfait r ́eduction.
		cmt = new ForfaitReduction(cmt);
		System.out.println("r ́eduction lgv : " + cmt.prixLocation(lgv));
		
		//Dupont achete en plus un forfait seuil, le seuil est à 2
		cmt = new ForfaitSeuil (cmt);
		System.out.println("Seuil1+Reduction lgv: " + cmt.prixLocation(lgv));
		System.out.println("Seuil2+Reduction lgv: " + cmt.prixLocation(lgv));
		System.out.println("Seuil3+Reduction lgv: " + cmt.prixLocation(lgv)); //rend 0
		
		//Dupont avec ses 2 forfaits loue un produit sold ́e
		Produit r4 = new ProduitSolde("RockyIV", (float) 10);
		System.out.println("Seuil1+Reduction+Solde rocky: " + cmt.prixLocation(r4));
		System.out.println("Seuil2+Reduction+Solde rocky: " + cmt.prixLocation(r4));
		System.out.println("Seuil3+Reduction+Solde rocky: " + cmt.prixLocation(r4));
	}
	
	public static void calculePrix(Produit p, Compte cmt)
	{
		System.out.println("Produit : " + p.getNom());
		System.out.println("Prix : " + cmt.prixLocation(p));
	}

}
