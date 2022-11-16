package tp7;

public class Remplir extends CommandeJeu
{
	Bidon bidonSource;
	
	public Remplir(Bidon bidonSource)
	{
		this.bidonSource = bidonSource;
		
		execute();
	}
	
	@Override
	public void execute()
	{
		setVolumeEauDeplace(bidonSource.remplir());
	}
	
	@Override
	public void undo()
	{
		bidonSource.volumeEau -= getVolumeEauDeplace();
	}
}
