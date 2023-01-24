package tp7;

public class Bidon
{
	int capacity;
	int volumeEau;

	public Bidon(Integer capacity)
	{
		this.capacity = capacity;
	}
	
	public int vider()
	{
		int buffEau = this.volumeEau;
		this.volumeEau = 0;
		return buffEau;
	}
	
	public int remplir()
	{
		int buffEau = this.volumeEau;
		this.volumeEau = this.capacity;
		return capacity - buffEau;
	}
	
	public int transvaser(Bidon bidonCible)
	{
		int buffEau;
		
		if (bidonCible.capacity - bidonCible.volumeEau < this.volumeEau) //volume sup au reste de la cible
		{
			buffEau = bidonCible.capacity - bidonCible.volumeEau;
			this.volumeEau -= bidonCible.capacity - bidonCible.volumeEau;
			bidonCible.volumeEau = bidonCible.capacity;
		}
		else //volume inf ou egal au reste de la cible
		{
			buffEau = this.volumeEau;
			bidonCible.volumeEau += this.volumeEau;
			this.volumeEau = 0;
		}
		
		return buffEau;
	}
}
