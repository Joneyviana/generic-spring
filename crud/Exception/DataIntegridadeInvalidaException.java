package com.generic.crud.Exception;

public class DataIntegridadeInvalidaException extends RuntimeException {
			private String message;
			
			public DataIntegridadeInvalidaException(String message) {
				this.message  = message;
			}

			public String getMessage() {
				return message;
			}

			public void setMessage(String message) {
				this.message = message;
			}
			
}
