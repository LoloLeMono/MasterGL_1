package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossierSuivi extends Remote
{
	
	void setInfoDossier(String info) throws RemoteException;
	String getInfoDossier() throws RemoteException;
}

