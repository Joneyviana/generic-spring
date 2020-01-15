package com.generic.crud.Exception;

public class ObjectNotFoundException  extends RuntimeException {
	private String message;
	
	public ObjectNotFoundException(String message) {
		this.message  = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
