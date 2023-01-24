package double_dispatch;

public class Utilisation
{

	public static void main(String[] args)
	{
		Produit lgv = new Produit("La grande vadrouille", 10.0);
		Client cl = new Client("Dupont");
		Compte cmt = new Compte(cl);
		
		Compte cmt2 = new CompteAvecReduction(cl, 50);
		System.out.println("CompteReduction : " + cmt2.prixLocation(lgv));
		Compte cmt3 = new CompteAvecSeuil(cl, 2); // le seuil est `a 2 par d ́efaut
		System.out.println("CompteSeuil : " + cmt3.prixLocation(lgv));
		System.out.println("CompteSeuil : " + cmt3.prixLocation(lgv));
		System.out.println("CompteSeuil : " + cmt3.prixLocation(lgv)); // doit afficher 0
		
		Produit r4 = new ProduitSolde("RockyIV", 10.0);
		System.out.println("CompteNormal + ProduitSoldé : " + cmt.prixLocation(r4));
		System.out.println("CompteReduction + ProduitSoldé : " + cmt2.prixLocation(r4));
	}
	
	public static void calculePrix(Produit p, Compte cmt)
	{
		System.out.println("Produit : " + p.getNom());
		System.out.println("Prix : " + cmt.prixLocation(p));
	}

}
