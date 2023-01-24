package tp7;

public class Transvaser extends CommandeJeu
{
	Bidon bidonSource;
	Bidon bidonCible;
	
	public Transvaser(Bidon bidonSource, Bidon bidonCible)
	{
		this.bidonSource = bidonSource;
		this.bidonCible = bidonCible;
		
		execute();
	}
	
	@Override
	public void execute()
	{
		setVolumeEauDeplace(bidonSource.transvaser(bidonCible));
	}
	
	@Override
	public void undo()
	{
		bidonSource.volumeEau += getVolumeEauDeplace();
		bidonCible.volumeEau -= getVolumeEauDeplace();
	}
}
