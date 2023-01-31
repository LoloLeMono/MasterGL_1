package com.example.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.example.common.rmi.Hello;

public class HelloImpl extends UnicastRemoteObject implements Hello {

	/* CONSTRUCTOR */
	protected HelloImpl() throws RemoteException {
		
	}

	/* METHODS */
	@Override
	public String sayHello() throws RemoteException {
		return "Hello world!";
	}

	@Override
	public void printHello() throws RemoteException {
		System.out.println("The server says: Hello world!");
	}

}
