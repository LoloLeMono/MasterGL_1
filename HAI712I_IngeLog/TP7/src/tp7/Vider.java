package tp7;

public class Vider extends CommandeJeu
{
	Bidon bidonSource;
	
	public Vider(Bidon bidonSource)
	{
		this.bidonSource = bidonSource;
		
		execute();
	}
	
	@Override
	public void execute()
	{
		setVolumeEauDeplace(bidonSource.vider());
	}
	
	@Override
	public void undo()
	{
		bidonSource.volumeEau += getVolumeEauDeplace();
	}
}
