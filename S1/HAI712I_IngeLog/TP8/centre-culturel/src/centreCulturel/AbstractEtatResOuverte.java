package centreCulturel;

public abstract class AbstractEtatResOuverte extends AbstractEtatSeminaux
{
	AbstractEtatResOuverte(Seminaire s)
	{
		super(s);
	}

	public abstract void clotureReservation();
}
