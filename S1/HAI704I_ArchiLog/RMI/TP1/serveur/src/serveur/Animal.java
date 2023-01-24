package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IAnimal;
import common.IDossierSuivi;

public class Animal extends UnicastRemoteObject implements IAnimal
{
	String nom, maitre;
	Object doss, race;
	
	protected Animal() throws RemoteException
	{
		Object doss = new DossierSuivi();
		Object race = new Race();
	}
	
	protected Animal(String name, String maitre) throws RemoteException
	{
		this.nom = name;
		this.maitre = maitre;
		
		/*
		Object doss = new DossierSuivi();
		Object race = new Race();
		*/
		
		System.out.println("Constructeur Animal");
	}
	
	protected Animal(String name, String maitre, String info) throws RemoteException
	{
		this.nom = name;
		this.maitre = maitre;
		
		Object doss = new DossierSuivi(info);
		Object race = new Race();
	}

	@Override
	public String getNom() throws RemoteException
	{
		return this.nom;
	}

	@Override
	public String getMaitre() throws RemoteException
	{
		return this.maitre;
	}
	
	@Override
	public void setNom(String nom) throws RemoteException
	{
		this.nom = nom;
	}
	
	@Override
	public void setDossierSuivi(Object doss) throws RemoteException
	{
		this.doss = doss;
	}
	
	@Override
	public void setRace(Object race) throws RemoteException
	{
		this.race = race;
	}
	
	@Override
	public Object getDossierSuivi() throws RemoteException
	{
		return this.doss;
	}

	@Override
	public Object getRace() throws RemoteException
	{
		return this.race;
	}

	@Override
	public void setNomMaitre(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
