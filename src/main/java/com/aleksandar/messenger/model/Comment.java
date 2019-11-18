package com.aleksandar.messenger.model;

public class Comment extends Base{

	private String comment;
	private ApplicationUser user;
	
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
