package dico;

import java.util.Scanner;

public class UtilisationDictionnaire
{
	static IDictionary dic;
	
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
	    System.out.println("1 - Ordered Dictionnary");
	    System.out.println("2 - Fast Dictionnary");
	    System.out.println("3 - Sorted Dictionnary");
	    int rep = sc.nextInt();
		
	    switch (rep)
	    {
	    	case 1:
	    		dic = new OrderedDico();
	    		
	    		dic.put("Allan", "Ministre de la santé");
	    		dic.put("Abdel", "Chimiste Algerien");
	    		dic.put("Lucas", "Ancien/Nouveau prophète");
	    		dic.put("Soso", "DJ Soso");
	    		
	    		dic.showDico();
	    		
	    		System.out.println("La key Abdel à comme valeur : " + dic.get("Abdel"));
				break;
				
	    	case 2:
	    		dic = new FastDico();
	    		
	    		dic.put("Momo", "Sait pour qui Didier à voté");
	    		dic.put("Didier", "Sait pour qui il vote");
	    		dic.put("Robin", "Capitaliste");
	    		dic.put("Phillipe", "Garagiste");
	    		
	    		dic.showDico();
	    		
	    		System.out.println("La key Robin à comme valeur : " + dic.get("Robin"));
				break;
				
	    	case 3:
	    		dic = new SortedDico();
	    		
	    		dic.put("1", "Je suis le prems");
	    		dic.put("2", "Je suis le deums");
	    		dic.put("3", "Je suis le troips");
	    		dic.put("4", "e suis le quatrps");
	    		
	    		dic.showDico();
	    		
	    		//System.out.println("La key Robin à comme valeur : " + dic.get("Robin"));
				break;
	    }    
		
	}
	
	/*
	private static void rep1(IDictionary dic)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("## 1 - Get value with key");
		System.out.println("## 2 - Add key/value");
		System.out.println("## 3 - IsEmpty ?");
		System.out.println("## 4 - Already contains the key ?");
		System.out.println("## 5 - Get size");
		int rep = sc.nextInt();

		switch (rep)
		{
			case 1:
				sc.nextLine();
			    String rep1 = sc.nextLine();
			
			case 2:
		}
		
	}
	*/
}
