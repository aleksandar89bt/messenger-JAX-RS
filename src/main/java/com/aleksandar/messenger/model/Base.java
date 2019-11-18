package com.aleksandar.messenger.model;

import java.util.Date;

public class Base {
	
	private int id;
	private Date created;
	private Date updated;
	
	public Base() {
		this.created = new Date();
		this.updated = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	

}
