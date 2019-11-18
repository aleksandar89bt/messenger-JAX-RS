package com.aleksandar.messenger.model;

public class ApplicationUser extends Base{
	private String firstName;
	private String lastName;
	private Upload upload;
	
	public ApplicationUser() {
		super();
	}
	
	public ApplicationUser(String firstName, String lastName) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public ApplicationUser(int id, String firstName, String lastName) {
		super();
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Upload getUpload() {
		return upload;
	}
	public void setUpload(Upload upload) {
		this.upload = upload;
	}
	
	
}
