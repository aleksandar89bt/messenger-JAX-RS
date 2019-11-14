package com.aleksandar.messenger.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.Post;
import com.aleksandar.messenger.service.PostService;
import com.aleksandar.messenger.utils.HelperUtils;

public class PostServiceImpl implements PostService{

	public static Map<Integer, Post> posts = new HashMap<>();
	public static Map<Integer, ApplicationUser> users = ProfileServiceImpl.users;
	
	static {
		posts.put(1, new Post(1, "Test post.", users.get(1)));
	}
	
	@Override
	public List<Post> getPosts() {
		return posts.entrySet()
				.stream()
				.map(m -> m.getValue())
				.collect(Collectors.toList());
	}
	
	

	@Override
	public List<Post> getPostsByUserId(int userId) {
		return posts.entrySet()
				.stream()
				.map(m -> m.getValue())
				.filter(p -> p.getUser().getId() == userId)
				.collect(Collectors.toList());
	}



	@Override
	public Response addPost(Post post, int userId) {
		ApplicationUser user = HelperUtils.findUserById(users, userId);
		int id = posts.size() + 1;
		post.setId(id);
		post.setUser(user);
		posts.put(id, post);
		return Response.status(Response.Status.CREATED).build();
	}

}
