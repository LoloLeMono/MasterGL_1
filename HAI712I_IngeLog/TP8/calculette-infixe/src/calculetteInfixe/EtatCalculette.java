package calculetteInfixe;

abstract class EtatCalculette
{
	static protected enum operations {plus, moins, mult, div};
	abstract int enter(String s) throws CalculetteException;
	Calculette calc; // faire les calculs qui ne se trouvent pas dans état
	
	EtatCalculette(Calculette c) // sera appeller par super pour que chaque etat conaisse une instance de calculette
	{
		calc = c;
	}
}
