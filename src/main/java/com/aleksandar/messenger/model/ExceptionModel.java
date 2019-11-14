package com.aleksandar.messenger.model;

import java.util.Date;

public class ExceptionModel {

	private String error;
	private Date date;
	
	public ExceptionModel() {
		super();
	}
	public ExceptionModel(String error, Date date) {
		super();
		this.error = error;
		this.date = date;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
