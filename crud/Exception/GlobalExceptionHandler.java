package com.generic.crud.Exception;



import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler({ObjectNotFoundException.class})
	public  ResponseEntity<?> handleException(ObjectNotFoundException exception, WebRequest web ) {
		ApiResponse response = new ApiResponse(404,exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler({AuthorizationException.class})
	public  ResponseEntity<?> handle(AuthorizationException exception, WebRequest web ) {
		ApiResponse response = new ApiResponse(401,exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler({DataIntegridadeInvalidaException.class})
	public  ResponseEntity<?> handle(DataIntegridadeInvalidaException exception, WebRequest web ) {
		ApiResponse response = new ApiResponse(400,exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}

