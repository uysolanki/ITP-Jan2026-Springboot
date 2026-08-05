package com.itp.ITPJan2026Springboot.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message)
	{
		super(message);
	}
}
