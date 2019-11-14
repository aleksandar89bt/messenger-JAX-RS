package com.aleksandar.messenger.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.Comment;

public interface CommentService {
	List<Comment> getCommentsByPostId(int postId);
	Response addComment(int postId, Comment comment, int userId);
}
