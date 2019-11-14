package com.aleksandar.messenger.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

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
		post.getComments().add(comment);
		return Response.status(Response.Status.CREATED).build();
	}

	private Post findPostById(int id) {
		if (posts.size() < id - 1) {
			return null;
		}
		return posts.get(id);
	}

}
