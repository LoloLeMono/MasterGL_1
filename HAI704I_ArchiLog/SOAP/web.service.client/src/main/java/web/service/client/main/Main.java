package web.service.client.main;

import java.net.MalformedURLException;
import java.net.URL;

import web.service.client.Calculator;
import web.service.client.CalculatorSoap;

public class Main {

	public static void main(String[] args)
	{
		try {
			URL url = new URL ("http://www.dneonline.com/calculator.asmx?wsdl");
			Calculator serviceImpl = new Calculator(url);
			CalculatorSoap proxy = serviceImpl.getCalculatorSoap();
			
			int x = 2, y = 15;
			
			System.out.println("x = 2");
			System.out.println("y = 15");
			System.out.println("x + y = " + proxy.add(x, y));
			System.out.println("x - y = " + proxy.subtract(x, y));
			System.out.println("y - x = " + proxy.subtract(y, x));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
