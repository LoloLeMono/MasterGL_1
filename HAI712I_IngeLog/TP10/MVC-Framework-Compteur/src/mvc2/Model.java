package mvc2;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class Model
{
	//Implante MVC incluant le schéma Observateur
    //Un modèle connait indirectement ses vues 
	//Il est un observé, voir https://www.oodesign.com/observer-pattern
	
	//quand un modèle change, ses vues sont prévenues
  
	//A ecrire la méthode notify du schéma Observateur
	//également nommée changed dans le schéma MVC
	//how permet de rajouter des inforamtion supplémentaire au chamgement du model
  
	public void changed(Object how) //notify
	{
		for (View v : MV_Association.getViews(this))
		{
			v.update(this);
			//v.update(how);
		}
	}
}
