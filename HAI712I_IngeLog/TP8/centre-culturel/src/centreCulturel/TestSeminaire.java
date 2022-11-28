package centreCulturel;

public class TestSeminaire
{

	public static void main(String[] args)
	{
		Seminaire s = new Seminaire();
		s.ouvertureReservation(); //etat 1 : accepte get et set;
		
		s.inscription("Phillipe");
		//System.out.println(s.getResult());
	}
}
