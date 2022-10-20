package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote
{
	String getNom() throws RemoteException;
	String getMaitre() throws RemoteException;
	Object getRace() throws RemoteException;
	Object getDossierSuivi() throws RemoteException;
	
	void setNom(String nom) throws RemoteException;
	void setNomMaitre(String nom) throws RemoteException;
	void setDossierSuivi(Object doss) throws RemoteException;
	void setRace(Object race) throws RemoteException;
	
}
