package dico;

public abstract class AbstractDictionary implements IDictionary
{
	Object[] tabKey;
	Object[] tabValue;
	
	public AbstractDictionary()
	{
		this.tabKey = new Object[0];
		this.tabValue = new Object[0];
	}
	
	public AbstractDictionary(int taille)
	{
		this.tabKey = new Object[taille];
		this.tabValue = new Object[taille];
	}
	
	public Object get(Object key) // Retourne la valeur associer à une key donné
	{
		int ind = indexOf(key);
		
		if (ind == -1)
		{
			System.err.println("Aucune valeur pour la clé : " + key.toString());
			return null;
		}
		else
		{
			return tabValue[indexOf(key)];
		}	
	}
	
	public IDictionary put(Object key, Object value) // Rajoute un couple key/value dans le dictionnaire
	{		
		int ind = indexOf(key);
		
		if (ind == -1)
		{
			ind = newIndexOf(key);
		}
		
		tabKey[ind] = key;
		tabValue[ind] = value;
		
		return this;
	}
	
	public Boolean isEmpty() // Est vide
	{
		if (size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean containsKey(Object key) // Contiens la key
	{
		for (int i=0; i<size(); i++)
		{
			if (tabKey[i] == key)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Integer size() // Renvoi le nb d'élément d'un tab
	{
		return tabKey.length;
	}
	
	public void showDico() // Affiche le dictionnaire
	{
		if (isEmpty())
		{
			System.out.println("Tableaux vides");
		}
		else
		{
			System.out.print("[ ");
			for (int i=0; i<this.tabKey.length; i++)
			{
				if (i == this.tabKey.length-1)
				{
					System.out.println(this.tabKey[i] + " ; " + this.tabValue[i] + " ]");
				}
				else
				{
					System.out.print(this.tabKey[i] + " ; " + this.tabValue[i] + " | ");
				}
				
			}
		}
	}
	
	public abstract Integer indexOf(Object key);
	public abstract Integer newIndexOf(Object key);
}
