package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IRace;

public class Race extends UnicastRemoteObject implements IRace
{
	String nomRace;
	Integer dureeVie;

	protected Race() throws RemoteException
	{
	}
	
	@Override
	public String getNomRace() throws RemoteException
	{
		// TODO Auto-generated method stub
		return nomRace;
	}

	@Override
	public Integer getDureeVie() throws RemoteException
	{
		// TODO Auto-generated method stub
		return dureeVie;
	}

	@Override
	public void setNomRace(String nom) throws RemoteException
	{
		this.nomRace = nom;
	}

	@Override
	public void setDureeVie(Integer age) throws RemoteException
	{
		this.dureeVie = age;
	}

}
