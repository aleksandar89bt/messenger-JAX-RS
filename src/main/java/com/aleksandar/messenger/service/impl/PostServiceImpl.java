package com.aleksandar.messenger.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import com.aleksandar.messenger.exception.ResourceAccessDeniedException;
import com.aleksandar.messenger.exception.ResourceNotFoundException;
import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.ExceptionModel;
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
		post.setCreated(new Date());
		post.setUpdated(new Date());
		posts.put(id, post);
		return Response.status(Response.Status.CREATED).build();
	}



	@Override
	public Post updatePost(int id, Post post, int userId){
		Post foundPost = posts.entrySet()
		.stream()
		.filter(p -> p.getKey() == id)
		.findFirst()
		.map(m -> m.getValue())
		.orElseThrow(() -> new ResourceNotFoundException("Post with this id doesn't exist"));
		if(foundPost.getUser().getId() != userId) {
			throw new ResourceAccessDeniedException("You don't have permission to modify this post");
		}
		foundPost.setText(post.getText());
		foundPost.setUpdated(new Date());
		return foundPost;
	}



	@Override
	public Response deletePost(int postId, int userId) {
		Post post = posts.get(postId);
		if (post == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new ExceptionModel("Post not found", new Date())).build();
		}
		if (post.getUser().getId() == userId) {
			posts.remove(postId);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.status(Response.Status.FORBIDDEN).entity(new ExceptionModel("You don't have permission to modify this post", new Date())).build() ;
	}
	
	

}
