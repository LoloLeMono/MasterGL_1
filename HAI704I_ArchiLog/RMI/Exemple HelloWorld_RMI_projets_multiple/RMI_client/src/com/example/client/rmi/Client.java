package com.example.client.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.example.common.rmi.Hello;

public class Client {
	private Client() {}
	
	public static void main(String[] args) {
		String host = (args.length < 1)? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Hello obj = (Hello) registry.lookup("Hello");
			
			System.out.println(obj);
			String response = obj.sayHello();
			System.out.println("Response: " + response);
			obj.printHello();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
