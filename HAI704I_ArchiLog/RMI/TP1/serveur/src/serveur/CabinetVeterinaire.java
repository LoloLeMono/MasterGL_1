package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.IAnimal;
import common.ICabinetVeterinaire;
import common.ILambda;
import common.IVeterinaire;

public class CabinetVeterinaire extends UnicastRemoteObject implements ICabinetVeterinaire
{
	private List<IAnimal> l_Animaux;
	private List<IVeterinaire> l_Clients;
	
	protected CabinetVeterinaire() throws RemoteException {
		super();
		this.l_Animaux = new ArrayList<IAnimal>();
		this.l_Clients = new ArrayList<IVeterinaire>();
	}
	
	@Override
	public IAnimal find(String nomAnimal) throws RemoteException
	{
		IAnimal retAnimal = null;
		
		for(IAnimal animal : l_Animaux)
		{
			if (animal.getNom() == nomAnimal)
			{
				retAnimal = animal;
				System.out.println(nomAnimal + "a été trouvé !");
			}
		}
		
		System.out.println(nomAnimal + "n'existe pas");
		return new Animal();
	}

	@Override
	public void addVeterinaire(IVeterinaire client) throws RemoteException
	{
		l_Clients.add(client);
	}

	@Override
	public void add(ILambda lAnimal)
	{
		
		try
		{
			IAnimal animal = new Animal(lAnimal.getNom(), lAnimal.getNomMaitre());
			l_Animaux.add(animal);
			System.out.println("I've added the animal " + animal.getNom() + " to the register !");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		animal.setRace(bundleInfo.getRace());
		animal.setEspece(bundleInfo.getEspece());
		animal.setSuivi(bundleInfo.getDossierSuivi());
		animal.setEspeceClass(bundleInfo.getEspeceClass());
		*/
	}

}
