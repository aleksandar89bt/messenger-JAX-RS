package com.aleksandar.messenger.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private int id;
	private String text;
	private ApplicationUser user;
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {
		super();
	}
	
	
	public Post(int id, String text, ApplicationUser user) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Message [id=" + id + ", text=" + text + ", user=" + user + "]";
	}
	
}
