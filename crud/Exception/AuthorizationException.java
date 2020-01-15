package com.generic.crud.Exception;

public class AuthorizationException  extends RuntimeException {
	private String message;
	
	public AuthorizationException(String message) {
		this.message  = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
