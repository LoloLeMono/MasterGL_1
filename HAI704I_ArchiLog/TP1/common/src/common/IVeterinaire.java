package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVeterinaire extends Remote
{
	public String getNom() throws RemoteException;
	public void setNom(String nom) throws RemoteException;
	//public void alert(int n) throws RemoteException;
}
