package com.tprest.exceptions;

public class HotelNotFoundException extends HotelException
{
	public HotelNotFoundException()
	{
		
	}
	
	public HotelNotFoundException(String message)
	{
		super(message);
	}
}
