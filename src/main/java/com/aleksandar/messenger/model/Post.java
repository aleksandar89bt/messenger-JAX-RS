package com.aleksandar.messenger.model;

import java.util.ArrayList;
import java.util.List;

public class Post extends Base{

	private String text;
	private ApplicationUser user;
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {
		super();
	}
	
	
	public Post(int id, String text, ApplicationUser user) {
		super();
		this.setId(id);
		this.text = text;
		this.user = user;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ApplicationUser getUser() {
		return user;
	}
	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "Message [id=" + getId() + ", text=" + text + ", user=" + user + "]";
	}
	
}
