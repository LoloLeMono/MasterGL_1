package dico;

public class OrderedDico extends AbstractDictionary
{
	public OrderedDico()
	{
		super();
	}
	
	@Override
	public Integer indexOf(Object key) // Rend l'index de la clé donné et -1 si la clé n'existe pas
	{
		
		int ind = -1;
		
		for (int i=0; i<this.size(); i++)
		{
			// System.out.println("Index of : Is " + this.tabKey[i] + " == " + key + " ?");
			
			if (this.tabKey[i] == key)
			{
				ind = i;
			}
		}
		
		return ind;
	}
	
	@Override
	public Integer newIndexOf(Object key) // Rajoute une nouvelle case à la fin du tab des clé et retourne son index
	{
		
		Object[] newTabKey = new Object[this.size()+1];
		Object[] newTabValue = new Object[this.size()+1];
		int newInd = 0;
		
		// System.out.println("Dans indexOf : Taille du tab buffer = " + newTabKey.length);
		
		for (int i=0; i<size(); i++)
		{
			newTabKey[i] = tabKey[i];
			newTabValue[i] = tabValue[i];
		}
		
		tabKey = newTabKey;
		tabValue = newTabValue;
		
		newInd = size() -1;
		
		return newInd;
		
	}
}
