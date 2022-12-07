package mvc2;

import javax.swing.*;
import java.awt.*;

public class CompteurView2 extends View
{

	JProgressBar progressCompteur = new JProgressBar(-100, 100);
	
	public CompteurView2(Model m, Controller c) 
	{
		super(m, c);
		this.add(progressCompteur);
		this.update("valeur");
	}

	public void update(Object how)
	{
		// how n'est pas utilisé dans cet exemple
		int newVal = ((Compteur) m).getValeur();
		progressCompteur.setValue(newVal);
		//setText déclenche le ré-affichage
	}

}
