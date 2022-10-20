package com.example.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	/* METHODS */
	String sayHello() throws RemoteException;
	void printHello()  throws RemoteException;
}
