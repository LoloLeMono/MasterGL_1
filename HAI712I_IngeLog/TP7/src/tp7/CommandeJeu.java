package tp7;

public abstract class CommandeJeu
{
	private int volumeEauDeplace;
	
	public CommandeJeu()
	{
		
	}
	
	public void execute()
	{
		
	}
	
	public void undo()
	{
		
	}
	
	public int getVolumeEauDeplace()
	{
		return this.volumeEauDeplace;
	}
	
	public void setVolumeEauDeplace(int vol)
	{
		this.volumeEauDeplace = vol;
	}
}
