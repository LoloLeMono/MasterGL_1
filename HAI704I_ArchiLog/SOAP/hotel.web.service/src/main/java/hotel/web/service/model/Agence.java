package hotel.web.service.model;

public class Agence
{
	
	private String nom;
	private String mdp;
	private Integer reduction;
	
	public Agence(String nomAgence, String motdepasse, Integer reduction)
	{
		this.nom = nomAgence;
		this.mdp = motdepasse;
		this.reduction = reduction;
	}
	
	public void setReduction(Integer red)
	{
		reduction = red;
	}
	
	public String getNomAgence()
	{
		return nom;
	}
	
	public String getMotdepasse()
	{
		return mdp;
	}
	
	public Integer getReduction()
	{
		return reduction;
	}
	
	@Override
	public String toString()
	{
		return "[nomAgence=" + nom + ", motdepasse=" + mdp + ", reduction=" + reduction + "]";
	}
}