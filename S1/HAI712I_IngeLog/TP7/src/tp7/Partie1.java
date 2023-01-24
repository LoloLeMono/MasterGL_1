package tp7;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie1
{
	int volume;
	ArrayList<CommandeJeu> histo;
	ArrayList<Bidon> bidons;
	
	public Partie1(int nbBidons, int[] cap, int volGoal)
	{
		bidons = new ArrayList<Bidon>();
		histo = new ArrayList<CommandeJeu>();
		
		for (int i=0; i<nbBidons; i++)
		{
			bidons.add(new Bidon(cap[i]));
		}
		
		volume = volGoal;
		
		for (int i=0; i<bidons.size(); i++)
		{
			System.out.println("Bidon n° "+ i + " : " + bidons.get(i).volumeEau + " / " + bidons.get(i).capacity);
		}
	}

	public void jouer()
	{
		Integer choix;
		
		do
		{
			System.out.println("--------NOUVEAU TOUR---------");
			
			System.out.println("Choisir un bidon : ");
			Scanner sc = new Scanner(System.in);  // Create a Scanner object

		    Integer numBidon = sc.nextInt();  // Read user input
		    
		    System.out.println("1 - Vider , 2 - Remplir, 3 - Transvaser, 4 - Undo, 5 - Quitter");
		    choix = sc.nextInt();
		    
		    switch(choix)
		    {
		    	case 1:
		    		histo.add(new Vider(bidons.get(numBidon)));
		    		break;
		    		
		    	case 2:
		    		histo.add(new Remplir(bidons.get(numBidon)));
		    		break;
		    		
		    	case 3:
		    		System.out.println("Avec que bidon souhaitez vous transvaser le bidon " + numBidon);
		    		Integer numBidon2 = sc.nextInt();
		    		histo.add(new Transvaser(bidons.get(numBidon), bidons.get(numBidon2)));
		    		break;
		    		
		    	case 4:
		    		histo.get(histo.size()-1).undo();
		    		histo.remove(histo.size()-1);
		    		break;
		    }
		    
		    for (int i=0; i<bidons.size(); i++)
			{
				System.out.println("Bidon n° "+ i + " : " + bidons.get(i).volumeEau + " / " + bidons.get(i).capacity);
				if (bidons.get(i).volumeEau == volume)
				{
					System.out.println("ON A GAGNEEEEE !!!");
					System.out.println("Historique des coups : " + histo);
					choix = 5;
					break;
				}
			}

		} while (choix != 5);
		
		System.out.println("--------FIN DU GAME---------");
		
	}
}
