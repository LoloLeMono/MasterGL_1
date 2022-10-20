package dico;

public interface IDictionary
{
	Object get(Object key); // Retourne la valeur associer à une key donné
	IDictionary put(Object key, Object value); // Rajoute un couple key/value dans le dictionnaire
	Boolean isEmpty(); // Est vide
	Boolean containsKey(Object key); // Contiens la key donné
	Integer size(); // Renvoi la taille du dictionnaire
	
	void showDico(); // Affiche le dictionnaire
}
