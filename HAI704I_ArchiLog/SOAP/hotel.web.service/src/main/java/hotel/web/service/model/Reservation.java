package hotel.web.service.model;

public class Reservation
{
	
	private Client client;
	private String debut;
	private String fin;
	
	public Reservation(Client client, String debut, String fin)
	{
		this.client = client;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Client getClient()
	{
		return client;
	}
	
	public String getDebut()
	{
		return debut;
	}
	
	public String getFin()
	{
		return fin;
	}
	
	@Override
	public String toString()
	{
		return "[cli=" + client + ", debut=" + debut + ", fin=" + fin + "]";
	}
}
