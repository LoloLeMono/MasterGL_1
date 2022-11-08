package reservationHotels.web.service.model;

public class Adresse
{
	String pays, ville, rue;
	Integer numero;
	
	public Adresse(String pays, String ville, Integer numero, String rue)
	{
		super();
		this.pays = pays;
		this.ville = ville;
		this.numero = numero;
		this.rue = rue;
	}
	
	public String getPays()
	{
		return pays;
	}
	
	public void setPays(String pays)
	{
		this.pays = pays;
	}
	
	public String getVille()
	{
		return ville;
	}
	
	public void setVille(String ville)
	{
		this.ville = ville;
	}
	
	public Integer getNumero()
	{
		return numero;
	}
	
	public void setNumero(Integer numero)
	{
		this.numero = numero;
	}
	
	public String getRue()
	{
		return rue;
	}
	
	public void setRue(String rue)
	{
		this.rue = rue;
	}
}
