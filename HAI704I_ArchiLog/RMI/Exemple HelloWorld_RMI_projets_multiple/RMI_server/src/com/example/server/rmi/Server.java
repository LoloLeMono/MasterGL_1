package com.example.server.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	/* CONSTRUCTOR */
	public Server() {
		
	}
	
	/* METHODS */
	public static void main(String[] args) {
		try {
			HelloImpl obj = new HelloImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			
			if (registry == null)
				System.err.println("Registry not found on port 1099");
			else {
				registry.bind("Hello", obj);
				System.err.println("Server ready");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
