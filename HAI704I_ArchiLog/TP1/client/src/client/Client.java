package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.IAnimal;
import common.ICabinetVeterinaire;
import common.IDossierSuivi;
import common.ILambda;
import common.IVeterinaire;
import common.Lambda;

public class Client
{
	private Client(){}
	
	public static void main(String[] args)
	{
		String host = (args.length < 1)? null : args[0];
		
		try
		{
			Registry registry = LocateRegistry.getRegistry(host);
			
			ICabinetVeterinaire stub = (ICabinetVeterinaire) registry.lookup("Cabinet");
			
			// IDossierSuivi dossierTitouan = (IDossierSuivi) registry.lookup("dossierTitouan");
			IVeterinaire client = new Veterinaire();
			client.setNom("LoloLeVeto");
			
			stub.addVeterinaire(client);
			
			ILambda lAnimal = new Lambda();
			lAnimal.setNom("Medor");
			lAnimal.setNomMaitre("Maitre-Martin");
			lAnimal.setRace("Labrador");
			
			stub.add(lAnimal);
			IAnimal stubAnimal  = (IAnimal) stub.find("Medor");
			System.out.println(stubAnimal.getNom());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}