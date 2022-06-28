package com.revature.exceptions;

public class RegisterAccountFailedException extends RuntimeException{
	
	public RegisterAccountFailedException(String message) {
		super(message);
	}
}
