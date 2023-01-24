package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRace extends Remote
{
	String getNomRace() throws RemoteException;
	Integer getDureeVie() throws RemoteException;
	
	void setNomRace(String nom) throws RemoteException;
	void setDureeVie(Integer age) throws RemoteException;
}
