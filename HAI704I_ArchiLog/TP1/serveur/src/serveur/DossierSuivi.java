package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IDossierSuivi;

public class DossierSuivi extends UnicastRemoteObject implements IDossierSuivi
{
	String infoDossier;

	protected DossierSuivi() throws RemoteException
	{
		super();
		this.infoDossier = "test";
		System.out.println("Constructeur Dossier");
		// TODO Auto-generated constructor stub
	}
	
	protected DossierSuivi(String infoDoss) throws RemoteException
	{
		super();
		setInfoDossier(infoDoss);
	}

	@Override
	public String getInfoDossier() throws RemoteException
	{
		// TODO Auto-generated method stub
		return infoDossier;
	}

	@Override
	public void setInfoDossier(String infos) throws RemoteException
	{
		this.infoDossier = infos;
	}

}
