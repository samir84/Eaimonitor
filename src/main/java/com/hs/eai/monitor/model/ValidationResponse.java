package com.hs.eai.monitor.model;

import java.util.List;

public class ValidationResponse {
	private String status;
	private Object object;
	private String message;
	
	private List<ErrorMessage> errorMessageList;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ErrorMessage> getErrorMessageList() {
		return this.errorMessageList;
	}
	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
	
}