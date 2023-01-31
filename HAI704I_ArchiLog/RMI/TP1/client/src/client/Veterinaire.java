package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IAnimal;
import common.IVeterinaire;

public class Veterinaire extends UnicastRemoteObject implements IVeterinaire
{
	protected Veterinaire() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	String nom;
	@Override
	public String getNom() throws RemoteException
	{
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public void setNom(String nom) throws RemoteException
	{
		this.nom = nom;
	}

}
