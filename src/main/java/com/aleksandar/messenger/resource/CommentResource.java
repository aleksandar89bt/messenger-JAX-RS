package com.aleksandar.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.Comment;
import com.aleksandar.messenger.service.CommentService;
import com.aleksandar.messenger.service.impl.CommentServiceImpl;

@Path("/posts/{postId}")
public class CommentResource {

	private CommentService service = new CommentServiceImpl(); 
	
	@POST
	@Path("/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("postId") int postId, Comment comment, @QueryParam("userId") int userId) {
		return service.addComment(postId, comment, userId);
	}
	
	@GET
	@Path("/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("postId") int postId) {
		return service.getCommentsByPostId(postId);
	}
}
