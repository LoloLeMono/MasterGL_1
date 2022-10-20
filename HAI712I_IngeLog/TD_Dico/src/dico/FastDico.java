package dico;

public class FastDico extends AbstractDictionary
{
	public FastDico()
	{
		super(5);
	}
	
	public Boolean mustGrow()
	{
		if (this.size() >= (this.tabKey.length/4)*3)
		{
			return true;
		}
		
		return false;
	}
	
	public void grow()
	{
		Object[] newTabKey = new Object[this.tabKey.length*2];
		Object[] newTabValue = new Object[this.tabKey.length*2];
		
		for (int i=0; i<this.tabKey.length; i++)
		{
			newTabKey[i] = this.tabKey[i];
			newTabValue[i] = this.tabValue[i];
		}
		
		for (int i=this.tabKey.length; i<this.tabKey.length*2; i++)
		{
			newTabKey[i] = null;
			newTabValue[i] = null;
		}
	}
	
	@Override
	public Integer size() // Renvoi la taille du dictionnaire sans compter les cases vides
	{
		int size = 0;
		
		for (int i=0; i<this.tabKey.length; i++)
		{
			if (this.tabKey[i] != null)
			{
				size++;
			}
		}
		
		return size;
	}

	@Override
	public Integer indexOf(Object key) // Rend l'index de la clé donné et -1 si la clé n'existe pas
	{
		
		int ind = -1;
		
		for (int i=0; i<this.tabKey.length; i++) //commencer la recherche à partir du hash de l'objet
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
	public Integer newIndexOf(Object key) // Trouve une case vide au hasard et retourne son index
	{
		int ind = 0;
		
		if (this.tabKey.length != 0)
		{
			if (mustGrow())
			{
				grow();
			}
			
			int i = this.hashCode() % this.tabKey.length;
			
			// System.out.println("Index = " + i);
			
			while (tabKey[i] != null)
			{
				i = (i+1) % this.tabKey.length;
				System.out.println(this.tabKey[i] + " à la case " + i);
			}
				
			ind = i;
		}
		
		return ind;
	}

}
