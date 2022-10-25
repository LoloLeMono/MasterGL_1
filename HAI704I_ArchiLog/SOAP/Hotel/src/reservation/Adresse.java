package reservation;

public class Adresse
{
	String pays;
	String ville;
	Integer numero;
	Integer rue;
	
	public Adresse(String pays, String ville, Integer numero, Integer rue)
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
	
	public Integer getRue()
	{
		return rue;
	}
	
	public void setRue(Integer rue)
	{
		this.rue = rue;
	}
}
