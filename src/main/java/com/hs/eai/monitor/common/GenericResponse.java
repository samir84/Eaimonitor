package com.hs.eai.monitor.common;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class GenericResponse implements Serializable{

	public GenericResponse() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private Object Object;
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getObject() {
		return Object;
	}
	public void setObject(Object object) {
		Object = object;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
