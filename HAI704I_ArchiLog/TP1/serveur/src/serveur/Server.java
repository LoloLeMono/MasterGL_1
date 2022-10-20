package serveur;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.IAnimal;
import common.ICabinetVeterinaire;
import common.IDossierSuivi;

public class Server
{

	/* CONSTRUCTOR */
	public Server() {}
	
	/* METHODS */
	public static void main(String[] args)
	{
		
		try
		{
			ICabinetVeterinaire objCabinet = new CabinetVeterinaire();
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			if (registry == null) {
				System.err.println("Registry not found on port 1099");
			}
			else {
				registry.bind("Cabinet", objCabinet);
				System.err.println("Server ready");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}