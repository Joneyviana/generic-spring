package com.generic.crud.Exception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			 {
		ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        try {
			response.getWriter().write(mapper.writeValueAsString(new ExceptionApi(401,System.currentTimeMillis(),
					"Não esta authenticado")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
		
	}
}

class ExceptionApi {
	private int status;
	private String timeStamp;
	private String message;
	public int getStatus() {
		return status;
	}
	public ExceptionApi(int status, long timeStamp,String Message){
		this.status = status;
		this.message = Message;
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
		this.timeStamp = df2.format(new Date(timeStamp));
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}