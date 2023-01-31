package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinetVeterinaire extends Remote
{
	public IAnimal find(String nomAnimal) throws RemoteException;
	// public void add(Bundle bundleInfo) throws RemoteException;
	public void addVeterinaire(IVeterinaire client) throws RemoteException;
	public void add(ILambda lAnimal) throws RemoteException;
}