package com.aleksandar.messenger.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.Post;

public interface PostService {

	List<Post> getPosts();
	List<Post> getPostsByUserId(int userId);
	Response addPost(Post post, int userId);
}
