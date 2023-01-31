package double_dispatch;

public class Produit
{
	private String nom;
	private Double prix;
	
	public Produit(String nom, Double prix)
	{
		super();
		this.nom = nom;
		this.prix = prix;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Double prixLocation()
	{
		return prix;
	}

	public void setPrix(Double prix)
	{
		this.prix = prix;
	}
}
