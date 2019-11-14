package com.aleksandar.messenger.model;

public class Comment {

	private int id;
	private String comment;
	private ApplicationUser user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ApplicationUser getSender() {
		return user;
	}
	public void setSender(ApplicationUser user) {
		this.user = user;
	}
	
}
