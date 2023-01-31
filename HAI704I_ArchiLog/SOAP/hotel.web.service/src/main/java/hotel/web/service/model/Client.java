package hotel.web.service.model;

public class Client
{
	
	private String nom;
	private String prenom;
	private double carte;
	
	public Client(String n, String p, double c)
	{
		nom = n;
		prenom = p;
		carte = c;
	}

	@Override
	public String toString()
	{
		return "[nom=" + nom + ", prenom=" + prenom + ", carte=" + carte + "]";
	}
	
	
}