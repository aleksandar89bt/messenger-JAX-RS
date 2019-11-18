package com.aleksandar.messenger.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import com.aleksandar.messenger.exception.ResourceAccessDeniedException;
import com.aleksandar.messenger.exception.ResourceNotFoundException;
import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.Comment;
import com.aleksandar.messenger.model.ExceptionModel;
import com.aleksandar.messenger.model.Post;
import com.aleksandar.messenger.service.CommentService;
import com.aleksandar.messenger.utils.HelperUtils;

public class CommentServiceImpl implements CommentService {

	public Map<Integer, Post> posts = PostServiceImpl.posts;
	public Map<Integer, ApplicationUser> users = ProfileServiceImpl.users;

	@Override
	public List<Comment> getCommentsByPostId(int postId) {
		Post post = findPostById(postId);
		if(post == null) {
			return new ArrayList<>();
		}
		return post.getComments();
	}
	

	@Override
	public Comment updateComment(int id, int postId, Comment comment, int userId) {
		Comment c = posts.entrySet()
		.stream()
		.filter(p -> p.getKey() == postId)
		.map(m -> m.getValue().getComments())
		.flatMap(m -> m.stream())
		.filter(p -> p.getId() == id)
		.findFirst()
		.orElseThrow(() -> new ResourceNotFoundException("This comment does not exist"));
		
		if(c.getSender().getId() != userId) {
			throw new ResourceAccessDeniedException("You don't have permission to modify this comment");
		}
		c.setComment(comment.getComment());
		c.setUpdated(new Date());
		return c;
	}


	@Override
	public Response addComment(int postId, Comment comment, int userId) {
		Post post = findPostById(postId);
		if (post == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new ExceptionModel("Post not found", new Date())).build();
		}
		int size = post.getComments().size();
		int id = size + 1;
		ApplicationUser user = HelperUtils.findUserById(users, userId);
		comment.setId(id);
		comment.setSender(user);
		comment.setCreated(new Date());
		comment.setUpdated(new Date());
		post.getComments().add(comment);
		return Response.status(Response.Status.CREATED).build();
	}

	
	@Override
	public Response deleteComment(int postId, int commentId, int userId) {
		List<Comment> comments = posts.entrySet()
		.stream()
		.filter(p -> p.getKey() == postId)
		.flatMap(m -> m.getValue().getComments().stream())
		.collect(Collectors.toList());
		boolean isRemoved = comments.removeIf(p -> p.getId() == commentId && p.getSender().getId() == userId);
		Post post = findPostById(postId);
		if(post == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new ExceptionModel("Post not found", new Date())).build();
		}
		post.setComments(comments);
		return isRemoved ? Response.status(Response.Status.NO_CONTENT).build() : Response.status(Response.Status.NOT_FOUND).entity(new ExceptionModel("Comment not found", new Date())).build();
	}


	private Post findPostById(int id) {
		if (posts.size() < id - 1) {
			return null;
		}
		return posts.get(id);
	}

}
