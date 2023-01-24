package dico;

public class SortedDico extends OrderedDico
{
	
	public SortedDico()
	{
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer indexOf(Object key) // Rend l'index de la clé donné et -1 si la clé n'existe pas
	{
		
		int ind = -1;
		
		for (int i=0; i<this.size(); i++)
		{
			// System.out.println("Index of : Is " + this.tabKey[i] + " == " + key + " ?");
			
			if (((Comparable<Object>) this.tabKey[i]).compareTo((Comparable<Object>) key) == 0)
			{
				ind = i;
			}
		}
		
		return ind;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer newIndexOf(Object key) // Rajoute une nouvelle case pour garder l'ordre et retourne son index
	{
		
		Object[] newTabKey = new Object[this.size()+1];
		Object[] newTabValue = new Object[this.size()+1];
		int newInd = 0;
		
		if (this.size() == 0)
		{
			tabKey = newTabKey;
			tabValue = newTabValue;
			
			return 1;
		}
		else
		{
			while(((Comparable<Object>) this.tabKey[newInd]).compareTo((Comparable<Object>) key) < 0)
			{
				newInd++;
			}
			
			// COPIER LE DEBUT
			System.arraycopy(this.tabKey, 0, newTabKey, 0, newInd-1); // (tabDeb, ind début, tabNew, casDebNewTab, nbCaseCopié)
			System.arraycopy(this.tabValue, 0, newTabValue, 0, newInd-1); // (tabDeb, ind début, tabNew, casDebNewTab, nbCaseCopié)
			
			// COPIER LA FIN EN SAUTANT UNE CASE
			System.arraycopy(this.tabKey, newInd, newTabKey, newInd+1, this.size()+1); // (tabDeb, ind début, tabNew, casDebNewTab, nbCaseCopié)
			System.arraycopy(this.tabValue, newInd, newTabValue, newInd+1, this.size()+1); // (tabDeb, ind début, tabNew, casDebNewTab, nbCaseCopié)
			
			tabKey = newTabKey;
			tabValue = newTabValue;
			
			return newInd;
		}
				
	}
}
